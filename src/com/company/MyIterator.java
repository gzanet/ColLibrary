package com.company;

public interface MyIterator<T> {
    T getNext();

    boolean hasNext();

    T next();

    boolean remove();
}
