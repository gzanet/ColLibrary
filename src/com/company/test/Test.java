package com.company.test;

import com.company.ConcreteClass.*;
import com.company.Interfacce.ZZCollection;
import com.company.Interfacce.ZZHashTable;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZList;
import com.company.ZZFunctions.*;

import java.awt.*;


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
        l.sort(new ZZBiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return 0;
            }
        });
        for(int i=1;i<20;i++){
            l.insertHead(i);
        }
        l.insertAt(2,100).insertAt(l.size()-2, 200).insertHead(-20).insertTail(-40);
        stampa_zzIterable("Inserimento effetuato", l);

        l.sort(new ZZBiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer i, Integer j) {
                return i-j;
            }
        });
        stampa_zzIterable("Ordinamento fatto",l);

        l.removeAllExcept(new ZZTest<Integer>() {
            @Override
            public boolean test(Integer e) {
                return e%2==0;
            }
        });

        stampa_zzIterable("Rimozione dispari",l);

        ZZList<Integer> l2=l.removeFrom(3);
        stampa_zzIterable("divisione from",l);
        stampa_zzIterable("divisione from",l2);

        ZZList<Integer> l3=l2.removeUntil(3);
        stampa_zzIterable("divisione until",l2);
        stampa_zzIterable("divisione until",l3);

        l3.sort(new ZZBiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer i, Integer j) {
                return i.compareTo(j);
            }
        });

   }

   private static void line(){
       System.out.println();
   }

   public static void test_PriorityQueue(){
        Lista<String> l = new Lista<>();
        l.insertTail("uno");
        l.insertTail("due");
        l.insertTail("tredici");
        l.insertTail("ventidue");
        l.insertTail("nove");
        ZZFunction<String, Double> f = (String s) -> Double.valueOf(s.length());
        PriorityQueue<String> q = new PriorityQueue<>(f, l);

        ZZConsumer<Object> cons = e -> System.out.println(e);
        q.forEachElement(cons);
        System.out.println();

        System.out.println("getMax(): " + q.getMax());
        line();
        System.out.println("extractMax(): " + q.extractMax());
        line();
        q.forEachElement(cons);
        line();

        ZZFunction<Rectangle, Double> priority = (Rectangle r) -> r.getX() * r.getY();
        ZZFunction<String, Rectangle> mapping = (String s) -> new Rectangle(s.length(), s.length() * 2);
        PriorityQueue<Rectangle> q2 = q.map(mapping, priority);
        line();

        q2.forEachElement(cons);
        ZZIterator<Rectangle> it = q2.getIterator();
        line();

        while(it.hasNext())
            System.out.println(it.getNext());

   }

   public static void test_HashTable(){
       ZZBiFunction<Double,String,String> comp1 = (e1,e2) -> e1.equals(e2) == true ? 0.0 : 1.0;
        ZZHashTable<String> t = new HashTable<>(10, comp1, e->e.length()%3);
        t.add("Ciao");
        t.add("Ciao");
        t.add("CiaoComeStai");
        t.add("CiaoATuttiQuanti");
        t.add("a");
        t.add("Ciao");
        t.add("CiaoCiao");
        t.add("ab");
        t.add("CiaoCiaoCiao");
        t.add("CiaoComeStai");
        t.add("abcd");
        line();

        ZZConsumer<Object> cons = e -> System.out.println(e);
        t.forEachElement(cons);
        line();

        t.addHashFunction(e->e.length()*2%7);
        t.addHashFunction(e->e.length()%10);
        t.add("abcde");
        t.forEachElement(cons);
        line();

        System.out.println("t.containsValue Ciao "+t.containsValue("Ciao"));
        System.out.println("t.containsValue Cao "+t.containsValue("Cao"));
        line();

        ZZFunction<String,Rectangle> mappingFun = e->new Rectangle(e.length(),e.length()*2);
        ZZFunction<Rectangle,Integer> hashFun = e -> Math.toIntExact(Math.round(e.getX()));
        ZZBiFunction<Double,Rectangle,Rectangle> comp2 = (e1,e2) -> e1.getX()-e2.getX();

        ZZHashTable<Rectangle> t2 =  t.map(mappingFun, hashFun, comp2);
        t2.forEachElement(cons);
        line();

        t.delete("Ciao");
        t.forEachElement(cons);
        line();

        System.out.println(t.size());
        line();

   }
}
