package com.company;

import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNotFoundException;

public interface ZZList<T> extends ZZCollection<T> {

    void insertAt(int position, T elem);

    default void insertHead(T elem){
        insertAt(0, elem);
    }

    default void insertAt(int[] position, T elem){
        for (int aPosition : position) {
            insertAt(aPosition, elem);
        }
    }

    default void insertAt(int[] position, T[] elem) throws ZZInvalidArgumentException {
        if( position.length != elem.length) throw 
            new ZZInvalidArgumentException("the number of elements contained in the arrays must be the same");

        for(int i=0; i<position.length; i++){
            insertAt(position[i], elem[i]);
        }
    }

    void removeHead() throws ZZNotFoundException;

    void removeAt(int position) throws ZZNotFoundException;

    T getHead() throws ZZNotFoundException;

}
