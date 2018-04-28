package com.company;

public abstract class ZZLinkedList<T> implements ZZList<T> {
    /*
        MyNode<T> head;
        MyNode<T> n;
        MyNode<T> succ;

    public ZZLinkedList(MyNode<T> head);

    public void insertAt(int position, T elem) {
        n = head;
        for(int i=0; i<position; i++){

        }
    }
    */

    @Override
    public void removeHead() {

    }

    @Override
    public void removeAt(int position) {

    }

    @Override
    public T getHead() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ZZIterator<T> getIterator() {
        return null;
    }

}
