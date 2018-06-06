package com.company.ConcreteClass;

import com.company.Interfacce.ZZCollection;
import com.company.Interfacce.ZZIterable;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZQueue;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZDoubleNode;
import com.company.ZZNode.ZZSimpleNode;

public class Coda<T> implements ZZQueue<T> {


    private static final String ErrorEmptyContainer ="Errore: la coda è vuota";
    private int size;
    private ZZSimpleNode<T> head;
    private ZZSimpleNode<T> tail;

    public Coda(){
        size = 0;
        head= null;
        tail = null;
    }

    public Coda(ZZIterable<T> iterable){
        this();
        addAll(iterable);
    }


    @Override
    public void enqueue(T e){
        ZZSimpleNode<T> temp=new ZZSimpleNode<>(e);
        if(size==0){
            tail=temp;
            head=temp;
        }
        else {
            tail.setNext(temp);
            tail=temp;
        }
        size++;
    }

    @Override
    public T dequeue() throws ZZEmptyContainerException {
        checkEmpty();
        T elem=head.getElem();
        if(size==1){
            head=null;
            tail=null;
        }
        else{
            head=head.getNext();
        }
        size--;
        return elem;

    }

    @Override
    public T first() throws ZZEmptyContainerException {
        checkEmpty();
        return head.getElem();
    }

    @Override
    public int size() {
        return size;
    }

   @Override
    public void removeAll(){
        size=0;
        head=null;
        tail=null;
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {
        Coda<T> temp=new Coda<>();
        temp.addAllExcept(this,tester);
        head=temp.head;
        tail=temp.tail;
        size=temp.size;
    }

    @Override
    public <S> ZZCollection<S> map(ZZFunction<T,S> fun) {
        Coda<S> temp=new Coda<>();
        ZZIterator<T> it=getIterator();
        while(it.hasNext()){
            temp.enqueue(fun.apply(it.getNext()));
        }
        return temp;
    }

    @Override
    public ZZIterator<T> getIterator() {
        return new ZZIterator<T>() {
            ZZSimpleNode<T> p=head;
            @Override
            public boolean hasNext() {
                return p!=null;
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

    private void checkEmpty(){
        if(size==0){throw new ZZEmptyContainerException(ErrorEmptyContainer);}
    }
}
