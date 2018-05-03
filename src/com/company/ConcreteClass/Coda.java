package com.company.ConcreteClass;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZQueue;

public class Coda<T> implements ZZQueue<T> {

    @Override
    public void enqueue(T e) throws ZZNoAvailableSpaceException {

    }

    @Override
    public T dequeue() throws ZZEmptyContainerException {
        return null;
    }

    @Override
    public T first() throws ZZEmptyContainerException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ZZIterator<T> getIterator() {
        return null;
    }
}
