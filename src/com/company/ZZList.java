package com.company;

import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZTest;

public interface ZZList<T> extends ZZCollection<T> {

    default void insertHead(T elem){
        insertAt(0, elem);
    }
    void insertAt(int position, T elem);
    default void insertAt(ZZCollection<Integer> pos,  ZZCollection<T> col){ // utilizzare <? extends T>
        ZZIterator<T> it = col.getIterator();
        ZZIterator<Integer> jt = pos.getIterator();
        if( pos.size() != col.size() )
            throw new ZZInvalidArgumentException("size parameter of the collections must be the same");

        while( it.hasNext() )
            this.insertAt(jt.getNext(), it.getNext());
    }

    default void removeHead() throws ZZNotFoundException{ removeAt(0); }
    void removeAt(int position) throws ZZNotFoundException;
    default void removeAt(ZZCollection<Integer> pos,  ZZCollection<T> col){
        ZZIterator<Integer> it = pos.getIterator();
        if( pos.size() != col.size() )
            throw new ZZInvalidArgumentException("size parameter of the collections must be the same");

        while(it.hasNext() )
            this.removeAt(it.getNext());
    }

    T getAt(int position) throws ZZNotFoundException;
    default T getHead() throws ZZNotFoundException { return getAt(0); };
}
