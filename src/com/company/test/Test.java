package com.company.test;

import com.company.ConcreteClass.Coda;
import com.company.ConcreteClass.Lista;
import com.company.ConcreteClass.Pila;
import com.company.Interfacce.ZZCollection;
import com.company.Interfacce.ZZIterator;
import com.company.ZZFunctions.ZZBFunction;
import com.company.ZZFunctions.ZZFold;


public class Test {

   private  static <T> void stampa_zzIterable(String str, ZZCollection<T> p){
        System.out.println(str);
        int i=0;
        ZZIterator<T> it = p.getIterator();
        while(it.hasNext()){
            System.out.print(it.getNext() + " ");
            i++;
        }

       System.out.println();
       System.out.println("Size: " + p.size()+ " -- i: " + i);

    }
    public static void stack_test(){
        Pila<Integer> p=new Pila<>();
        for(int i=0;i<20;i++){
            p.push(i);
        }

        stampa_zzIterable("Inserimento effetuato", p);

        p.removeAllExcept(e -> (e % 2) == 0);

        stampa_zzIterable("Rimozione dispari", p);

        p.push(100);
        System.out.println("Size: " + p.size());
        System.out.println("top: " + p.top());
        System.out.println("pop: " + p.pop());
        System.out.println("Size: " + p.size());

        Pila<Integer> p2=new Pila<>(p);
        stampa_zzIterable("Nuova pila ", p2);

        p.removeAll();
        stampa_zzIterable("pila p vuota",p);

    }


    public static void queue_test(){
        Coda<Integer> p=new Coda<>();
        for(int i=0;i<20;i++){
            p.enqueue(i);
        }

        stampa_zzIterable("Inserimento effetuato", p);
        p.forEachElement((Integer i) -> System.out.println(String.format("for each, i: %d", i)));

        p.removeAllExcept(e -> (e % 2) == 0);

        stampa_zzIterable("Rimozione dispari", p);

        p.enqueue(100);
        System.out.println("Size: " + p.size());
        System.out.println("top: " + p.first());
        System.out.println("pop: " + p.dequeue());
        System.out.println("Size: " + p.size());

        Pila<Integer> p2=new Pila<>(p);
        stampa_zzIterable("Nuova coda ", p2);

        p.removeAll();
        stampa_zzIterable("coda p vuota",p);

        Pila<Integer> pila=new Pila<>();
        for(int i=0;i<20;i++){
            pila.push(i);
        }

        stampa_zzIterable("pila ", pila);

        p.addAllExcept(pila,e -> (e % 2) == 0);

        stampa_zzIterable("coda", p);
    }

    public static void fold_test(){
       int n=20;
       double controllo=0;
       int ris=0;
       ZZCollection<Integer> col=new Coda<>();
       for(int i=0;i<n;i++){
           controllo+=i;
           col.add(i);
       }
       ris=col.fold(0, new ZZFold<Integer, Integer>() {
           @Override
           public Integer apply(Integer acc, Integer elem) {
               return acc+elem;
           }
       });
       System.out.println(String.format("Somma: \n Atteso: %f \n Calcolato: %d",controllo,ris));

       double media= controllo/n;
       double r=col.fold(0.0, new ZZFold<Double, Integer>() {
           @Override
           public Double apply(Double acc, Integer elem) {
               return acc + elem.doubleValue()/n;
           }
       });
       System.out.println(String.format("Media: \n Atteso: %f \n Calcolato: %f",media, r));

    }

    public static void lista_test(){
        Lista<Integer> l=new Lista<>();
        for(int i=1;i<20;i++){
            l.insertHead(i);
        }
        l.insertAt(2,100);
        l.insertAt(l.size()-2, 200);
        stampa_zzIterable("Inserimento effetuato", l);

        l.sort(new ZZBFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer i, Integer j) {
                return i-j;
            }
        });
        stampa_zzIterable("Ordinamento fatto",l);
    }
}
