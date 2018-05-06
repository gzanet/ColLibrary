package com.company.ConcreteClass;

import com.company.Interfacce.ZZIterable;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZStack;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZSimpleNode;

public class Pila<T> implements ZZStack<T> {

    private static String ErrorEmptyContainer ="Errore: la coda è vuota";
    private int size;
    private ZZSimpleNode<T> head;

    public Pila(){
        size=0;
        head=null;
    }

    public Pila(ZZIterable iterable){
        this();
        addAll(iterable);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void removeAll() {
        head=null;
        size=0;
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {
        Pila<T> temp=new Pila<>();
        temp.addAllExcept(this,tester);
        removeAll();
        addAll(temp);
    }

    @Override
    public ZZIterator<T> getIterator() {
        return new ZZIterator<T>() {
            ZZSimpleNode<T> p=head;
            @Override
            public boolean hasNext() {
                return p==null;
            }

            @Override
            public T getNext() throws ZZEmptyContainerException {
                if(p==null){throw new ZZEmptyContainerException(ErrorEmptyContainer);}
                T ris=p.getElem();
                p=p.getNext();
                return ris;
            }
        };
    }

    @Override
    public void push(T e) {
        head=new ZZSimpleNode<>(e, head);
        size++;
    }

    @Override
    public T pop() throws ZZEmptyContainerException {
        if(size==0){throw new ZZEmptyContainerException(ErrorEmptyContainer);}
        T ris=head.getElem();
        head=head.getNext();
        size--;
        return ris;
    }

    @Override
    public T top() throws ZZEmptyContainerException {
        if(size==0){throw new ZZEmptyContainerException(ErrorEmptyContainer);}
        return head.getElem();
    }
}
