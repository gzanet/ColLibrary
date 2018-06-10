package com.company.ConcreteClass;

import com.company.Interfacce.ZZIterable;
import com.company.Interfacce.ZZPriorityQueue;
import com.company.Interfacce.ZZQueue;
import com.company.Interfacce.ZZStack;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZFold;
import com.company.ZZFunctions.ZZFunction;

public class SuperLista<T> extends Lista<T> implements ZZQueue<T>, ZZStack<T>, ZZPriorityQueue<T> {

    private ZZFunction<T,Double> priorityFun;

    public SuperLista(){
           this(new ZZFunction<T, Double>() {
               @Override
               public Double apply(T e) {
                   return 0.0;
               }
           });
    }

    public SuperLista(ZZIterable iterable){
        this();
        addAll(iterable);
    }

    public SuperLista(ZZFunction<T,Double> priorityFun){
        super();
        setPriorityFun(priorityFun);
    }

    public SuperLista(ZZIterable iterable, ZZFunction<T,Double> priorityFun ){
        this(priorityFun);
        addAll(iterable);
    }

    public void setPriorityFun(ZZFunction<T, Double> priorityFun) {
        this.priorityFun = priorityFun;
    }

    @Override
    public T getMax() {
        return fold(getHead(), new ZZFold<T, T>() {
            @Override
            public T apply(T acc, T elem) {
                return priorityFun.apply(acc)>priorityFun.apply(elem)?acc:elem;
            }
        });
    }

    @Override
    public T extractMax() {
        T ris=getMax();
        removeAt(indexOf(ris));
        return ris;
    }

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
        return new SuperLista<>(super.map(fun));
    }

    public <S> SuperLista<S> map(ZZFunction<T,S> fun, ZZFunction<S,Double> priorityFun){
        return new SuperLista<>(super.map(fun),priorityFun);
    }

}
