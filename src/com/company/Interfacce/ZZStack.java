package com.company.Interfacce;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZFunction;

public interface ZZStack<T> extends ZZCollection<T> {

    void push(T e) throws ZZNoAvailableSpaceException;

    T pop() throws ZZEmptyContainerException;

    T top()throws ZZEmptyContainerException;

    @Override
    default T remove()throws ZZEmptyContainerException {
        return pop();
    }

    @Override
    default void add(T e)throws ZZNoAvailableSpaceException {
        push(e);
    }

    @Override
    <S> ZZStack<S> map(ZZFunction<T,S> fun);

}
