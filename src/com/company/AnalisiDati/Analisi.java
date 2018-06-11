package com.company.AnalisiDati;

import com.company.ConcreteClass.Lista;
import com.company.Interfacce.ZZList;
import com.company.ZZFunctions.ZZBiFunction;
import com.company.ZZFunctions.ZZFold;
import com.company.ZZFunctions.ZZFunction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Analisi {

    public static void sort(ZZList<Double> list) {
        list.sort(new ZZBiFunction<Integer, Double, Double>() {
            @Override
            public Integer apply(Double t1, Double t2) {
                return t1.compareTo(t2);
            }
        });
    }

    public static int checkInt(String[] str, int i) {
        return str[i].equals("") ? -1 : Integer.parseInt(str[i]);
    }

    public static boolean checkBool(String[] str, int i) {
        return (!str[i].equals("") && str[i].equals("1"));
    }

    public static double checkDouble(String[] str, int i) {
        return str[i].equals("") ? -1 : Double.parseDouble(str[i]);
    }

    public static ZZList<Data> parseFile(String path) throws IOException {
        Lista<Data> list = new Lista<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        String cvsSplitBy = ",";
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] campi = line.split(cvsSplitBy);

            list.insertTail(new Data(
                    checkInt(campi, 0),
                    checkInt(campi, 1),
                    checkInt(campi, 2),
                    checkInt(campi, 3),
                    checkBool(campi, 4),
                    checkInt(campi, 5),
                    checkBool(campi, 6),
                    checkDouble(campi, 7),
                    checkBool(campi, 8),
                    checkBool(campi, 9)
            ));
        }
        return list;
    }

    public static <T> T quantile(ZZList<T> campione, Double level) {
        return campione.getAt(Double.valueOf(campione.size() * level).intValue());
    }

    public static <T> Double mean(ZZList<T> list, ZZFunction<T, Double> fun) {
        return mean(list.map(fun));
    }

    public static Double mean(ZZList<Double> list) {
        return list.fold(0.0, new ZZFold<Double, Double>() {
            @Override
            public Double apply(Double acc, Double elem) {
                return acc + elem;
            }
        }) / list.size();
    }

    public static <T> Double varianza(ZZList<T> list, ZZFunction<T, Double> fun) {
        return varianza(list.map(fun));
    }

    public static Double varianza(ZZList<Double> list) {
        return mean(list.map(new ZZFunction<Double, Double>() {
            @Override
            public Double apply(Double e) {
                return e * e;
            }
        })) - Math.pow(mean(list), 2.0);
    }

    public static void stampa(String str) {
        System.out.println(str);
    }

    public static <T> void summary(ZZList<T> campione, ZZFunction<T, Double> fun) {
        summary(campione.map(fun));
    }

    public static void summary(ZZList<Double> campione) {
        Double mean, first_q, second_q, third_q, min, max;

        sort(campione);

        mean = mean(campione);

        min = campione.getHead();
        max = campione.getTail();

        first_q = quantile(campione, 0.25);
        second_q = quantile(campione, 0.50);
        third_q = quantile(campione, 0.75);

        stampa(String.format("Media: %f \nMin: %f\nMax: %f\nPrimo quartile: %f\nSecondo quartile: %f\nTerzo quantile: %f\n", mean, min, max, first_q, second_q, third_q));
    }

    public static void bootstrap(ZZList<Double> list) {
        bootstrap(list, 1000);
    }

    public static void bootstrap(ZZList<Double> list, int b) {
        Random rand = new Random();
        ZZList<ZZList<Double>> matrix = new Lista<>();
        for (int i = 0; i < b; i++) {
            ZZList<Double> temp = new Lista<>();
            for (int j = 0; j < list.size(); j++) {
                Double t = list.getAt(rand.nextInt(list.size()));
                temp.insertHead(t);
            }
            matrix.insertTail(temp);
        }

        double theta = mean(list);

        ZZList<Double> stime = new Lista<>();

        matrix.fold(stime, new ZZFold<ZZList<Double>, ZZList<Double>>() {
            @Override
            public ZZList<Double> apply(ZZList<Double> acc, ZZList<Double> elem) {
                return acc.insertTail(mean(elem));
            }
        });

        Double var = varianza(stime);

        Double alfa = 0.05;

        sort(stime);

        Double lower = 2 * theta - quantile(stime, 1 - alfa / 2);
        Double upper = 2 * theta - quantile(stime, alfa / 2);

        stampa(String.format("Intervallo di livello 1-alfa, alfa=%f\n Lower: %f\n Upper: %f", alfa, lower, upper));


    }


    public static void main(String[] args) {
        try {
            ZZList<Data> campione = parseFile("/run/media/filippo/Volume/università/ada/ada esame 21/ncbirths.csv");
            summary(campione, new ZZFunction<Data, Double>() {
                @Override
                public Double apply(Data e) {
                    return e.getWeight();
                }
            });

            //elimino dati anomali

            stampa("Rimozione dati anomali\n");

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


            ZZList<Double> pesi = new Lista<>();
            campione.fold(pesi, new ZZFold<ZZList<Double>, Data>() {
                @Override
                public ZZList<Double> apply(ZZList<Double> acc, Data elem) {
                    if (!elem.isLowBirthWeight()) {
                        acc.add(elem.getWeight());
                    }
                    return acc;
                }
            });

            summary(pesi);

            bootstrap(pesi);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
