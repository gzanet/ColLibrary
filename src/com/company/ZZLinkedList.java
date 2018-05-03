package com.company;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

public class ZZLinkedList<T> implements ZZList<T> {

    ZZNode<T> head;
    ZZNode<T> tail;
    int size;
    class ZZNode<T>{
        private T elem;
        ZZNode<T> next;

        public ZZNode(T elem, ZZNode<T> next) {
            this.elem = elem;
            this.next = next;
        }

        public T getElem() { return this.elem; }

        public ZZNode<T> getNext(){ return this.next; }

        public void setElem(T data){ this.elem = data; }

        public void setNext(ZZNode<T> next){ this.next = next; }
    }

    public ZZLinkedList(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }
    public ZZLinkedList(T data){
        this.insertHead(data);
    }
    public ZZLinkedList(ZZCollection<T> c) {
        //throws ZZNullPointerException // meglio mettere <? extends T>
        ZZIterator<T> it = c.getIterator();
        this.insertHead(it.getNext());
        int pos = 0;
        while(it.hasNext())
            this.insertAt(++pos, it.getNext());
    }

    public int size() { return this.size; }
    public boolean isEmpty() { return this.size == 0; }

    public void insertAt(int position, T data) {
        if (position < 0 || position > getSize())
            throw new ZZNotFoundException("MyNodeList.insertAt(): cannot insert at position " + position);
        if (position == 0)
            head = new ZZNode<T>(data, head);
        else {
            ZZNode<T> n = head;
            while (position > 1) {
                n = n.getNext();
                position--;
            }
            n.setNext(new ZZNode<T>( data, n.getNext() ));
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
            ZZNode<T> n = head;
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
         ZZNode<T> node = head;
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
            private ZZNode<T> node;

            ZZListIterator(){
                position=0;
                node = (ZZNode<T>) head; // perchè è necessario il cast ?
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
        ZZNode<T> node = head;

        while(position > 0)
            node = node.getNext();
        node.setElem(value);
    }
}
