package com.company.Interfacce;

import com.company.ZZExceptions.ZZEmptyContainerException;

public interface ZZIterator<T> {

    default boolean hasNext(){
        return false;
    }

    default T getNext() throws ZZEmptyContainerException{
        return null;
    }
}
