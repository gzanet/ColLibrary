package com.company;

public interface ZZStack<T> extends ZZCollection<T> {

    void push(T e);

    T pop();

    T top();

    @Override
    default T remove() {
        return pop();
    }
    @Override
    default void add(T e){
        push(e);
    }
}
