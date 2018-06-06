package com.company.AnalisiDati;

import com.company.ConcreteClass.Lista;
import com.company.Interfacce.ZZList;
import com.company.ZZFunctions.ZZBFunction;
import com.company.ZZFunctions.ZZFold;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Random;

public class Analisi_main {
    public static Lista<Data> parseFile(String path) throws IOException {
        Lista<Data> list=new Lista<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        String cvsSplitBy = ",";
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] campi = line.split(cvsSplitBy);


            list.insertTail(new Data(
                    campi[0].equals("")?-1:Integer.parseInt(campi[0]),
                    campi[1].equals("")?-1:Integer.parseInt(campi[1]),
                    campi[2].equals("")?-1:Integer.parseInt(campi[2]),
                    campi[3].equals("")?-1:Integer.parseInt(campi[3]),
                    !campi[4].equals("") && campi[4].equals("1"),
                    campi[5].equals("")?-1:Integer.parseInt(campi[5]),
                    !(!campi[6].equals("") && campi[6].equals("0")),
                    campi[7].equals("")?-1:Double.parseDouble(campi[7]),
                    !campi[8].equals("") && campi[8].equals("0"),
                    !campi[9].equals("") && campi[9].equals("0")
                    ));
        }
        return list;
    }

    public static void stampa(String str){
        System.out.println(str);
    }

    public static <T>void summary(ZZList<T> campione, ZZFunction<T,Double> fun){
        Double mean, first_q, second_q, third_q, min, max;

        campione.sort(new ZZBFunction<Integer, T, T>() {
            @Override
            public Integer apply(T t1, T t2) {
                return fun.apply(t1).compareTo(fun.apply(t2));
            }
        });

        mean=campione.fold(0.0, new ZZFold<Double, T>() {
            @Override
            public Double apply(Double acc, T elem) {
                return acc+fun.apply(elem)/campione.size();
            }
        });

        min=fun.apply(campione.getHead());
        max=fun.apply(campione.getTail());

        first_q=fun.apply(campione.getAt(Double.valueOf(campione.size()*0.25).intValue()));
        second_q=fun.apply(campione.getAt(Double.valueOf(campione.size()*0.50).intValue()));
        third_q=fun.apply(campione.getAt(Double.valueOf(campione.size()*0.75).intValue()));

        stampa(String.format("Media: %f \nMin: %f\nMax: %f\nPrimo quartile: %f\nSecondo quartile: %f\nTerzo quantile: %f\n", mean, min, max, first_q,second_q,third_q ));

    }

    public static <T> Double mean(ZZList<Double> list){
        return list.fold(0.0, new ZZFold<Double, Double>() {
            @Override
            public Double apply(Double acc, Double elem) {
                return acc+elem;
            }
        })/list.size();

    }
    public static void bootstrap(ZZList<Double> list, int b){
        Random rand=new Random();
        ZZList<ZZList<Double>> matrix=new Lista<>();
        for(int i=0;i<b;i++){
            ZZList<Double> temp=new Lista<>();
            for(int j=0;j<list.size();j++){
                Double t=list.getAt(rand.nextInt(list.size()));
                temp.insertHead(t);
            }
            matrix.insertTail(temp);
        }

        double theta=mean(list);

        ZZList<Double> stime=new Lista<>();

        matrix.fold(stime, new ZZFold<ZZList<Double>, ZZList<Double>>() {
            @Override
            public ZZList<Double> apply(ZZList<Double> acc, ZZList<Double> elem) {
                return acc.insertTail(mean(elem));
            }
        });

        Double var=mean(stime.map(new ZZFunction<Double, Double>() {
            @Override
            public Double apply(Double e) {
                return e*e;
            }
        }))-Math.pow(mean(stime),2.0);

        Double alfa=0.05;

        stime.sort(new ZZBFunction<Integer, Double, Double>() {
            @Override
            public Integer apply(Double a, Double b) {
                return a.compareTo(b);
            }
        });

        Double lower=stime.getAt(Double.valueOf(b*(1-alfa/2)).intValue())*2*theta;
        Double upper=stime.getAt(Double.valueOf(b*alfa/2).intValue())*2*theta;

        stampa(String.format("Intervallo di livello 1-%f, lower: %f, upper: %f",alfa,lower,upper));



    }


    public static void main(String[] args) {
        try {
            Lista<Data> campione = parseFile("/run/media/filippo/Volume/università/ada/ada esame 21/ncbirths.csv");
            summary(campione, new ZZFunction<Data, Double>() {
                @Override
                public Double apply(Data e) {
                    return e.getWeight();
                }
            });

            //elimino dati anomali

            stampa("elimino dati anomali\n");
        /*Lista<Data> temp=new Lista<Data>(campione);
        temp.removeAllExcept(new ZZTest<Data>() {
            @Override
            public boolean test(Data e) {
                return !e.isLowBirthWeight();
            }
        });

        Lista<Double> pesi1=temp.map(new ZZFunction<Data, Double>() {
            @Override
            public Double apply(Data e) {
                return e.getWeight();
            }
        });*/

            Lista<Double> pesi = new Lista<>();
        campione.fold(pesi, new ZZFold<Lista<Double>, Data>() {
            @Override
            public Lista<Double> apply(Lista<Double> acc, Data elem) {
                if(!elem.isLowBirthWeight()){
                    acc.add(elem.getWeight());
                }
                return acc;
            }
        });

        summary(pesi, new ZZFunction<Double, Double>() {
            @Override
            public Double apply(Double e) {
                return e;
            }
        });
        bootstrap(pesi, 1000);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
