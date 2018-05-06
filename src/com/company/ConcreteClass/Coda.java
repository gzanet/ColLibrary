package com.company.ConcreteClass;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZQueue;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZSimpleNode;

public class Coda<T> implements ZZQueue<T> {

    private int size;
    private ZZSimpleNode<T> head;
    private ZZSimpleNode<T> tail;

    public Coda(){
        size = 0;
        head= null;
        tail = null;
    }

    public Coda(Iterable<T> iterable){

    }

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
    public void removeAll() {

    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {

    }

    @Override
    public ZZIterator<T> getIterator() {
        return null;
    }
}
