package com.company.Interfacce;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZBiFunction;
import com.company.ZZFunctions.ZZFunction;

//inserimento può lanciare eccezione
public interface ZZList<T> extends ZZCollection<T> {

    @Override
    default T remove() throws ZZEmptyContainerException {
        T e=getHead();
        removeHead();
        return e;
    }

    @Override
    default void add(T e)throws ZZNoAvailableSpaceException {
        insertTail(e);
    }

    default ZZList<T> insertHead(T elem){
        insertAt(0, elem);
        return this;
    }
    default ZZList<T> insertTail(T elem){
        insertAt(size()-1==-1?0:size()-1, elem);
        return this;
    }
    ZZList<T> insertAt(int position, T elem);
    ZZList<T> inserAt(int position, ZZCollection<T> col);


    default T removeHead() throws ZZEmptyContainerException{ return removeAt(0); }
    default T removeTail() throws ZZEmptyContainerException{ return removeAt(size()-1); }
    T removeAt(int position) throws ZZEmptyContainerException;
    ZZList<T> removeFrom(int position);
    ZZList<T> removeUntil(int position);


    T getAt(int position) throws ZZNotFoundException;
    default T getHead() throws ZZNotFoundException { return getAt(0); };
    default T getTail() throws ZZNotFoundException { return getAt(size()-1); };

    void setAt(int position, T elem);

    int indexOf(T elem);
    int lastIndeOf(T elem);
    default boolean isContained(T elem){
        return indexOf(elem)!=-1;
    }

    void sort(ZZBiFunction<Integer,T,T> confronto);

    <S> ZZList<S> map(ZZFunction<T, S> fun);
}
