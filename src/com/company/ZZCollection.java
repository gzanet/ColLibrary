package com.company;

import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

public interface ZZCollection<T> extends MyIterable<T>{

    int size();

    boolean isEmpty();

    T remove();// eccezione

    default void removeAll(){
        while( ! isEmpty() ) {
            remove();
        }
    }

    void removeAllExcept(ZZTest<T> tester);

    void add(T e);//eccezione

    default void addAll(ZZCollection<T> collection){
        MyIterator<T> it = getIterator();
        while( it.hasNext() )
            add( it.getNext() );
    }

    default void addAllExcept(ZZCollection<T> collection, ZZTest<T> tester){
        MyIterator<T> it = getIterator();
        while( it.hasNext() ) {
            T elem = it.getNext();
            if (tester.test(elem)) {
                add(elem);
            }
        }
    }

    <E> ZZCollection<E> map(ZZFunction<T, E> function);

}