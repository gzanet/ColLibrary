package com.company;

import com.company.ConcreteClass.Lista;
import com.company.ZZFunctions.ZZFunction;
import com.company.test.Test;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("sfigato");

        //ESEMPIO 1
        /*ZZFunction<Integer,Integer> addTwo = i -> i+2;
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);

        addTwo.apply(l.get(0));
        System.out.println(l);

        //ESEMPIO 2
        Lista<Integer> list = new Lista<>(13);
        list.add(2);
        list.add(3);
        list.add(5);

        Lista<Integer> list2 = new Lista<>(list);
        Lista<Integer> list3 = new Lista<>();
       // list3.addAllExcept(list2, ;
*/
    //Test.stack_test();
        //Test.queue_test();
        Test.fold_test();

    }

}

