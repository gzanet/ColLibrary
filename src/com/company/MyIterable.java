package com.company;

import com.company.ZZFunctions.MyConsumer;

public interface MyIterable<T> {

    MyIterator<T> getIterator();

    //accepts a single input argument and returns no result.
    default void ForEachElement(MyConsumer<? super T> f){
        MyIterator<T> it = getIterator();
        while( it.hasNext() )
            f.consume( it.getNext() );
    }


}
