package com.company.Interfacce;

import com.company.ZZFunctions.ZZConsumer;

public interface ZZIterable<T> {

    ZZIterator<T> getIterator();

    //accepts a single input argument and returns no result.
    default void forEachElement(ZZConsumer<? super T> f){
        ZZIterator<T> it = getIterator();
        while( it.hasNext() )
            f.consume( it.getNext() );
    }


}
