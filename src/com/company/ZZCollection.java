package com.company;

import com.company.ZZFunctions.MyFunction;
import com.company.ZZFunctions.MyTest;

public interface ZZCollection<T> extends MyIterable<T>{

    int size();

    boolean isEmpty();

    T remove();// eccezione

    default void removeAll(){
        while( ! isEmpty() ) {
            remove();
        }
    }

    void removeAllExcept(MyTest<T> tester);

    void add(T e);//eccezione

    default void addAll(ZZCollection<T> collection){
        MyIterator<T> it = getIterator();
        while( it.hasNext() )
            add( it.getNext() );
    }

    default void addAllExcept(ZZCollection<T> collection, MyTest<T> tester){
        MyIterator<T> it = getIterator();
        while( it.hasNext() ) {
            T elem = it.getNext();
            if (tester.test(elem)) {
                add(elem);
            }
        }
    }

    <E> ZZCollection<E> map(MyFunction<T, E> function);

}