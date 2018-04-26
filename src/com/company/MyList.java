package com.company;

import com.company.myExceptions.InvalidArgumentException;
import com.company.myExceptions.NotFoundException;

public interface MyList<T> extends MyCollection<T> {

    void insertAt(int position, T elem);

    default void insertHead(T elem){
        insertAt(0, elem);
    }

    default void insertAt(int[] position, T[] elem) throws InvalidArgumentException {
        if( position.length != elem.length) throw 
            new InvalidArgumentException("the number of elements contained in the arrays must be the same");

        for(int i=0; i<position.length; i++){
            insertAt(position[i], elem[i]);
        }
    }

    boolean contain(T e);

    boolean contain(Collection<T> c);

    T[] toArray();



    void removeHead() throws NotFoundException;

    void removeAt(int position) throws NotFoundException;

    T getHead() throws NotFoundException;

}
