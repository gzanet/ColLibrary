package com.company.ConcreteClass;

import com.company.Interfacce.*;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZBFunction;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZDoubleNode;

public class Lista<T> implements ZZList<T> {

    ZZDoubleNode<T> head;
    ZZDoubleNode<T> tail;
    int size;

    public Lista() {
        size = 0;
        head = null;
        tail = null;
    }

    public Lista(ZZIterable iterable) {
        this();
        addAll(iterable);
    }

    @Override
    public ZZList<T> insertHead(T elem) {
        if (size == 0) {
            head = new ZZDoubleNode<>(elem);
            tail = head;
        } else {
            head = new ZZDoubleNode<>(elem, head, null);
        }
        size++;
        return this;
    }

    @Override
    public ZZList<T> insertTail(T elem) {
        if (size == 0) {
            head = new ZZDoubleNode<>(elem);
            tail = head;
        } else {
            tail = new ZZDoubleNode<>(elem, null, tail);
        }
        size++;
        return this;
    }

    @Override
    public ZZList<T> insertAt(int position, T elem) throws ZZInvalidArgumentException {
        checkPosition(position);
        if (position >= size - 1) {
            return insertTail(elem);
        } else if (position == 0) {
            return insertHead(elem);
        } else {
            ZZDoubleNode<T> temp = getNode(position - 1);
            new ZZDoubleNode<T>(elem, temp.getNext(), temp);
            size++;
            return this;
        }
    }

    @Override
    public ZZList<T> inserAt(int position, ZZCollection<T> col) throws ZZInvalidArgumentException {
        //TODO controllare che funzioni
        checkPosition(position);
        boolean coda = false;
        ZZIterator<T> it = col.getIterator();
        if (position == 0 && it.hasNext()) { //verifico di inserire in testa
            insertHead(it.getNext());
            position++;
        }
        coda = position == size - 1;//sto inserendo in coda
        ZZDoubleNode<T> temp = getNode(position);//controllare
        while (it.hasNext()) {
            temp = new ZZDoubleNode<T>(it.getNext(), temp.getNext(), temp);
            size++;
        }
        if (coda) {
            tail = temp;
        }
        return this;
    }

    @Override
    public void removeAll() {
        removeLast();
    }

    public T removeHead() throws ZZEmptyContainerException {
        checkEmpty();
        if (size == 1) {
            return removeLast();
        } else {
            ZZDoubleNode<T> temp = head;
            head = head.getNext();
            temp.sconcatena();
            size--;
            return temp.getElem();
        }
    }

    public T removeTail() throws ZZEmptyContainerException {
        checkEmpty();
        if (size == 1) {
            return removeLast();
        } else {
            ZZDoubleNode<T> temp = tail;
            tail = tail.getPrev();
            temp.sconcatena();
            size--;
            return temp.getElem();
        }
    }

    @Override
    public T removeAt(int position) throws ZZInvalidArgumentException {
        //controlli testa,coda, input
        checkPosition(position);
        if (position == 0) {
            return removeHead();
        } else if (position == size - 1) {
            return removeTail();
        } else {
            ZZDoubleNode<T> temp = getNode(position);
            temp.sconcatena();
            size--;
            return temp.getElem();
        }
    }

    //fare senza ricreazione nodi
    //position compresa
    @Override
    public ZZList<T> removeFrom(int position) throws ZZInvalidArgumentException {

        checkPosition(position);
        Lista<T> temp = new Lista<T>();
        if(position==0){
            swap(temp);
        }
        else {
            temp.head = getNode(position);
            temp.tail = tail;
            temp.size = size - position;

            tail = temp.head.getPrev();
            tail.setNext();
            size = position;
        }

        /*while (position < size) {
            temp.insertHead(removeTail());
        }*/
        return temp;
    }

    @Override
    public ZZList<T> removeUntil(int position) throws ZZInvalidArgumentException {
        /*Lista<T> temp=new Lista<T>();
        while(position<size){
            temp.insertTail(removeHead());
        }
        return temp;*/
        Lista<T> temp = (Lista) removeFrom(position);
        swap(temp);
        return temp;
    }

    @Override
    public T getAt(int position) throws ZZEmptyContainerException, ZZInvalidArgumentException {
        checkEmpty();
        checkPosition(position);
        return getNode(position).getElem();


    }

    @Override
    public int indexOf(T elem) {
        int position = 0;
        ZZDoubleNode<T> temp = head;
        boolean trovato = false;
        while (position < size && !trovato) {
            trovato = temp.getElem().equals(elem);
            position++;
            temp = temp.getNext();
        }
        return trovato ? position - 1 : -1;

    }

    @Override
    public int lastIndeOf(T elem) {
        int position = size - 1;
        ZZDoubleNode<T> temp = tail;
        boolean trovato = false;
        while (position > 0 && !trovato) {
            trovato = temp.getElem().equals(elem);
            position--;
            temp = temp.getPrev();
        }
        return trovato ? position + 1 : -1;
    }

    @Override
    public void setAt(int p, T elem) throws ZZInvalidArgumentException {
        checkPosition(p);
        getNode(p).setElem(elem);

    }

    @Override
    public void sort(ZZBFunction<Integer, T, T> confronto) {
        /*Insertion sort*/
        T key;
        ZZDoubleNode<T> prev=head;
        ZZDoubleNode<T> i=size==0?null:head.getNext();
        while(i!=null){
            key = i.getElem();
            prev=i.getPrev();
            while (prev!=null && confronto.apply(prev.getElem(), key) > 0) {
                prev.swap(prev.getNext());
                prev=prev.getPrev();
            }
            i=i.getNext();
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {
        boolean testa = false;
        ZZDoubleNode<T> temp = head;
        while (!testa && temp != null) {
            if (tester.test(temp.getElem())) {
                testa = true;
                temp = temp.getNext();
            } else {
                removeHead();
                temp = head;
            }
        }
        while (temp != null && temp != tail) {
            if (!tester.test(temp.getElem())) {
                temp = temp.getNext();
                temp.getPrev().sconcatena();
                size--;
            } else {
                temp = temp.getNext();
            }
        }
        if (tail != null && !tester.test(tail.getElem())) {
            removeTail();
        }

    }

    @Override
    public <S> ZZCollection<S> map(ZZFunction<T, S> fun) {
        Lista<S> ris = new Lista<S>();
        ZZDoubleNode<T> temp = head;
        for (int i = 0; i < size; i++) {
            ris.insertTail(fun.apply(temp.getElem()));
            temp = temp.getNext();
        }
        return ris;
    }

    @Override
    public ZZIterator<T> getIterator() {
        return new ZZIterator<T>() {
            ZZDoubleNode<T> temp = head;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public T getNext() throws ZZEmptyContainerException {
                if (temp == null) {
                    throw new ZZEmptyContainerException("vuoto");
                }
                T elem = temp.getElem();
                temp = temp.getNext();
                return elem;
            }
        };
    }

    private void swap(Lista<T> list) {
        ZZDoubleNode<T> node = head;
        head = list.head;
        list.head = node;

        node = tail;
        tail = list.tail;
        list.tail = node;

        int s = size;
        size = list.size;
        list.size = s;
    }

    private T removeLast() {
        ZZDoubleNode<T> temp = head;
        size = 0;
        head = null;
        tail = null;
        return temp.getElem()==null?null:temp.getElem();
    }

    private void checkPosition(int p) throws ZZInvalidArgumentException {
        if (p < 0) {
            throw new ZZInvalidArgumentException("Errore: indice minore di 0");
        } else if (p >= size) {
            throw new ZZInvalidArgumentException("Errore: indice maggiore di size");
        }
    }

    private void checkEmpty() throws ZZEmptyContainerException{
        if (size == 0) {
            throw new ZZEmptyContainerException("Errore: lista vuota");
        }
    }

    private ZZDoubleNode<T> getNode(int position) throws ZZInvalidArgumentException{
        checkPosition(position);
        ZZDoubleNode<T> temp;
        if (position < size / 2) {//siamo più vicini alla testa
            temp = head;
            for (int i = 0; i < position; i++) {
                temp = temp.getNext();
            }
        } else {
            temp = tail;
            for (int i = size - 1; i > position; i--) {
                temp = temp.getPrev();
            }
        }
        return temp;
    }


}
