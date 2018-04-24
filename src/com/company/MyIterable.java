package com.company;

import java.io.InvalidClassException;

public interface MyIterable<T> {

    MyIterator<T> getIterator();

    // applica la funzione a ciascun elemento dell'Iterable, se la funzione non è valida lancia un eccezione
    /*
    default void applyToEach(MyFunction<? super T> f){
        MyIterator<T> it = getIterator();
        while( it.hasNext() )
            f.apply(this);
    }
    */

}
