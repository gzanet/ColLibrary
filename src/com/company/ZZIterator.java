package com.company;

import com.company.ZZExceptions.ZZEmptyContainerException;

public interface ZZIterator<T> {

    boolean hasNext();

    T getNext() throws ZZEmptyContainerException;
}
