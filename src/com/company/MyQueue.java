package com.company;

public interface MyQueue<T> extends MyCollection<T>{

    public void enqueue(T e); // throws exception

    public T dequeue(); // throw exception

    public T first();

    @Override
    default T remove() {
        return dequeue();
    }
    @Override
    default void add(T e){
        enqueue(e);
    }
}
