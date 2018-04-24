package com.company;

import com.company.myExceptions.NotFoundException;

public abstract class MyLinkedList<T> implements MyList<T>{
    /*
        MyNode<T> head;
        MyNode<T> n;
        MyNode<T> succ;

    public MyLinkedList(MyNode<T> head);

    public void insertAt(int position, T elem) {
        n = head;
        for(int i=0; i<position; i++){

        }
    }
    */

    @Override
    public void removeHead() throws NotFoundException {

    }

    @Override
    public void removeAt(int position) throws NotFoundException {

    }

    @Override
    public T getHead() throws NotFoundException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public MyIterator<T> getIterator() {
        return null;
    }

}
