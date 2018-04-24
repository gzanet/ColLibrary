package com.company;

import com.company.myFunctions.MyConsumer;

public interface MyIterable<T> {

    public MyIterator<T> getIterator();

    //accepts a single input argument and returns no result.
    public default void ForEachElement(MyConsumer<? super T> f){
        MyIterator<T> it = getIterator();
        while( it.hasNext() )
            f.consume( it.getNext() );
    }


}
