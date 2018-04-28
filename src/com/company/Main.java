package com.company;

import com.company.ZZFunctions.ZZFunction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("sfigato");

        ZZFunction<Integer,Integer> addTwo = i -> i+2;
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);

        addTwo.apply(l.get(0));
        System.out.println(l);


    }

}

