package com.company.Interfacce;

import com.company.ZZFunctions.ZZFunction;

public interface ZZPriorityQueue<T> extends ZZCollection<T> {

    public T getMax();

    public T extractMax();

    @Override
    default public T remove(){
        return extractMax();
    }

    @Override
    public <S> ZZPriorityQueue<S> map(ZZFunction<T,S> fun) ;
}
