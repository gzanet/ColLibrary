package com.company.ConcreteClass;

import com.company.Interfacce.ZZCollection;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZList;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZSimpleNode;

public class Lista<T> implements ZZList<T> {

    ZZSimpleNode<T> head;
    ZZSimpleNode<T> tail;
    int size;


    public Lista(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }
    public Lista(T data){
        this.insertHead(data);
    }
    public Lista(ZZIterator<T> it) {
        //throws ZZNullPointerException // meglio mettere <? extends T>
       // ZZIterator<T> it = c.getIterator();
        this.insertHead(it.getNext());
        int pos = 0;
        while(it.hasNext())
            this.insertAt(++pos, it.getNext());
    }

    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }

    @Override
    public T remove() throws ZZEmptyContainerException {
        T t=getHead();
        removeAt(0);
        return t;
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {
        
    }

    public void insertAt(int position, T data) {
        if (position < 0 || position > getSize())
            throw new ZZNotFoundException("MyNodeList.insertAt(): cannot insert at position " + position);
        if (position == 0)
            head = new ZZSimpleNode<T>(data, head);
        else {
            ZZSimpleNode<T> n = head;
            while (position > 1) {
                n = n.getNext();
                position--;
            }
            n.setNext(new ZZSimpleNode<T>( data, n.getNext() ));
        }
        size++;
    }
    public void add(T e) throws ZZNoAvailableSpaceException { this.insertAt(size, e); }

    public void removeAt(int position) throws ZZNotFoundException {
        if (position < 0 || position >= size())
            throw new ZZNotFoundException("MyNodeList.removeAt(): cannot remove at position " + position);
        if (position == 0)
            head = head.getNext();
        else {
            ZZSimpleNode<T> n = head;
            while (position-- > 1) {
                n = n.getNext();
            }
            n.setNext(n.getNext().getNext());
        }
        size--;
    }

    /*public T getHead() throws ZZNotFoundException {
        return getAt(0);
    }*/
    public T getTail() throws ZZNotFoundException { return getAt(size-1); }
    public T getAt(int position) throws ZZNotFoundException {
        ZZSimpleNode<T> node = head;
         for(int i=0; i<position; i++){
             node = node.getNext();
         }
         return node.getElem();
    }
    public int getSize(){
        return this.size;
    }
    public ZZIterator<T> getIterator() {
        class ZZListIterator<T> implements ZZIterator<T>{
            private int position;
            private ZZSimpleNode<T> node;

            ZZListIterator(){
                position=0;
                node = (ZZSimpleNode<T>) head; // perchè è necessario il cast ?
            }

            public boolean hasNext() {
                if(position == size-1)
                    return false;
                else
                    return true;
            }

            public T getNext() throws ZZEmptyContainerException{
                node = node.getNext();
                return node.getElem();
            }
        }
        return new ZZListIterator<T>();
    }

    public void setValue(int position, T value) throws ZZNotFoundException{
        ZZSimpleNode<T> node = head;

        while(position > 0)
            node = node.getNext();
        node.setElem(value);
    }
}
