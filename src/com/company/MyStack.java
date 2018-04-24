package com.company;

public interface MyStack<T> extends MyCollection<T> {

    public void push(T e);

    public T pop();

    public T top();

    @Override
    default T remove() {
        return pop();
    }
    @Override
    default void add(T e){
        push(e);
    }
}
