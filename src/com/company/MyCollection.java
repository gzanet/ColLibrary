package com.company;

import com.company.myFunctions.MyFunction;
import com.company.myFunctions.MyTest;

public interface MyCollection<T> extends MyIterable<T>{

    int size();

    boolean isEmpty();

    public T remove();// eccezione

    public default void removeAll(){
        while( ! isEmpty() ) {
            remove();
        }
    }

    public void removeAllExcept(MyTest<T> tester);

    public void add(T e);//eccezione

    public default void addAll( MyCollection<T> collection){
        MyIterator<T> it = getIterator();
        while( it.hasNext() )
            add( it.getNext() );
    }

    public default void addAllExcept(MyCollection<T> collection, MyTest<T> tester){
        MyIterator<T> it = getIterator();
        while( it.hasNext() ) {
            T elem = it.getNext();
            if (tester.test(elem)) {
                add(elem);
            }
        }
    }

    public <E> MyCollection<E> map(MyFunction<T,E> function);

}