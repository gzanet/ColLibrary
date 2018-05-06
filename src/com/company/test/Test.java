package com.company.test;

import com.company.ConcreteClass.Coda;
import com.company.ConcreteClass.Pila;
import com.company.Interfacce.ZZCollection;
import com.company.Interfacce.ZZIterator;


public class Test {

   private  static <T> void stampa_zzIterable(String str, ZZCollection<T> p){
        System.out.println(str);
        System.out.println("Size: " + p.size());
        ZZIterator<T> it = p.getIterator();
        while(it.hasNext()){
            System.out.print(it.getNext() + " ");
        }

       System.out.println();
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
}