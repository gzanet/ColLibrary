package com.company.ConcreteClass;

import com.company.Interfacce.ZZQueue;
import com.company.Interfacce.ZZStack;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZFunction;

public class SuperLista<T> extends Lista<T> implements ZZQueue<T>, ZZStack<T> {
    @Override
    public T remove(){
        return removeHead();
    }

    @Override
    public void add(T e){
        insertHead(e);
    }

    @Override
    public void enqueue(T e) throws ZZNoAvailableSpaceException {
        insertTail(e);
    }

    @Override
    public T dequeue() throws ZZEmptyContainerException {
        return removeHead();
    }

    @Override
    public T first() throws ZZEmptyContainerException {
        return getHead();
    }

    @Override
    public void push(T e) throws ZZNoAvailableSpaceException {
        insertHead(e);
    }

    @Override
    public T pop() throws ZZEmptyContainerException {
        return removeHead();
    }

    @Override
    public T top() throws ZZEmptyContainerException {
        return getHead();
    }

    @Override
    public <S> SuperLista<S> map(ZZFunction<T,S> fun){
        return null;
    }

}
