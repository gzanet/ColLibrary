package com.company.ConcreteClass;

import com.company.ConcreteClass.Coda;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZConsumer;
import com.company.ZZFunctions.ZZTest;
import com.company.Interfacce.ZZIterable;
import com.company.Interfacce.ZZIterator;

public class CodaSyncr<T> extends Coda<T> {
    @Override
    public synchronized T dequeue() throws ZZEmptyContainerException {
        return super.dequeue();
    }

    @Override
    public synchronized T first() throws ZZEmptyContainerException {
        return super.first();
    }

    @Override
    public synchronized int size() {
        return super.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return super.isEmpty();
    }

   /* @Override
    public synchronized void removeAllExcept(ZZTest<T> tester) {
        super.removeAllExcept(tester);
    }*/

    /*@Override
    public synchronized <E> ZZCollection<E> map(ZZFunction<T, E> function) {
        return super.map(function);
    }*/

    @Override
    public synchronized ZZIterator<T> getIterator() {
        return super.getIterator();
    }

    @Override
    public synchronized T remove() throws ZZEmptyContainerException {
        return super.remove();
    }
/*
    @Override
    public synchronized void add(T e) throws ZZNoAvailableSpaceException {
        super.add(e);
    }
*/
    @Override
    public synchronized void removeAll() {
        super.removeAll();
    }

    @Override
    public synchronized void addAll(ZZIterable<T> collection) throws ZZNoAvailableSpaceException {
        super.addAll(collection);
    }

    @Override
    public synchronized void addAllExcept(ZZIterable<T> collection, ZZTest<T> tester) throws ZZNoAvailableSpaceException {
        super.addAllExcept(collection,tester);
    }

    @Override
    public synchronized void forEachElement(ZZConsumer<? super T> f) {
        super.forEachElement(f);
    }

    @Override
    public synchronized void enqueue(T e) throws ZZNoAvailableSpaceException {
        super.enqueue(e);
    }
}
