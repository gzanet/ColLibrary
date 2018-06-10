package com.company.ConcreteClass;

import com.company.Interfacce.ZZCollection;
import com.company.Interfacce.ZZIterable;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZStack;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZSimpleNode;

public class Pila<T> implements ZZStack<T> {

    private static String ErrorEmptyContainer ="Errore: la pila è vuota";
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
    public <S> Pila<S> map(ZZFunction<T, S> fun) {
        Pila<S> temp=new Pila<>();
        ZZIterator<T> it=getIterator();
        while(it.hasNext()){
            temp.push(fun.apply(it.getNext()));
        }
        return new Pila<S>(temp);
    }

    @Override
    public ZZIterator<T> getIterator() {
        return ZZSimpleNode.getIterator(head);
    }

    @Override
    public void push(T e) {
        head=new ZZSimpleNode<>(e, head);
        size++;
    }

    @Override
    public T pop() throws ZZEmptyContainerException {
        checkEmpty();
        T ris=head.getElem();
        head=head.getNext();
        size--;
        return ris;
    }

    @Override
    public T top() throws ZZEmptyContainerException {
        checkEmpty();
        return head.getElem();
    }

    private void checkEmpty(){
        if(size==0){throw new ZZEmptyContainerException(ErrorEmptyContainer);}
    }
}
