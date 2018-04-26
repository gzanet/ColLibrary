package com.company;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;

public interface ZZQueue<T> extends ZZCollection<T> {

    void enqueue(T e) throws ZZNoAvailableSpaceException;

    T dequeue()throws ZZEmptyContainerException;

    T first() throws ZZEmptyContainerException;

    @Override
    default T remove() throws ZZEmptyContainerException {
        return dequeue();
    }
    @Override
    default void add(T e)throws ZZNoAvailableSpaceException{
        enqueue(e);
    }
}
