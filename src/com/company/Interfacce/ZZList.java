package com.company.Interfacce;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZExceptions.ZZNotFoundException;

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
        insertHead(e);
    }

    default void insertHead(T elem){
        insertAt(0, elem);
    }
    default void insertTail(T elem){
        insertAt(size()-1, elem);
    }//controllo già vuota
    void insertAt(int position, T elem);

    default void insertAt(ZZCollection<Integer> pos,  ZZCollection<T> col){ // utilizzare <? extends T>
        ZZIterator<T> it = col.getIterator();
        ZZIterator<Integer> jt = pos.getIterator();
        if( pos.size() != col.size() )
            throw new ZZInvalidArgumentException("size parameter of the collections must be the same");

        while( it.hasNext() )
            this.insertAt(jt.getNext(), it.getNext());
    }

    default T removeHead() throws ZZEmptyContainerException{ return removeAt(0); }
    default T removeTail() throws ZZEmptyContainerException{ return removeAt(size()-1); }
    T removeAt(int position) throws ZZEmptyContainerException;
    default void removeAt(ZZCollection<Integer> pos,  ZZCollection<T> col) throws ZZEmptyContainerException{/*TODO da rivedere*/
        ZZIterator<Integer> it = pos.getIterator();
        if( pos.size() != col.size() )
            throw new ZZInvalidArgumentException("size parameter of the collections must be the same");

        while(it.hasNext() )
            this.removeAt(it.getNext());
    }

    T getAt(int position) throws ZZNotFoundException;
    default T getHead() throws ZZNotFoundException { return getAt(0); };
    default T gettail() throws ZZNotFoundException { return getAt(size()-1); };


    int indexOf(T elem);
    default boolean isContained(T elem){
        return indexOf(elem)!=-1;
    }

    void sort();



}
