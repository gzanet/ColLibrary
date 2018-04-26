package com.company;

public interface ZZQueue<T> extends ZZCollection<T> {

    void enqueue(T e); // throws exception

    T dequeue(); // throw exception

    T first();

    @Override
    default T remove() {
        return dequeue();
    }
    @Override
    default void add(T e){
        enqueue(e);
    }
}
