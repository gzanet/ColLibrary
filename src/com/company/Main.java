package com.company;

import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("sfigato");

        //ESEMPIO 1
        ZZFunction<Integer,Integer> addTwo = i -> i+2;
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);

        addTwo.apply(l.get(0));
        System.out.println(l);

        //ESEMPIO 2
        ZZLinkedList<Integer> list = new ZZLinkedList<>(13);
        list.add(2);
        list.add(3);
        list.add(5);

        ZZLinkedList<Integer> list2 = new ZZLinkedList<>(list);
        ZZLinkedList<Integer> list3 = new ZZLinkedList<>();
        list3.addAllExcept(list2,  ZZTe;



    }

}

