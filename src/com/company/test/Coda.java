package com.company.test;

import com.company.ZZCollection;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZIterator;
import com.company.ZZQueue;

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
    public void removeAllExcept(ZZTest<T> tester) {

    }

    @Override
    public <E> ZZCollection<E> map(ZZFunction<T, E> function) {
        return null;
    }

    @Override
    public ZZIterator<T> getIterator() {
        return null;
    }
}
