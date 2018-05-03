package com.company.Interfacce;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZTest;

import java.util.Iterator;

public interface ZZCollection<T> extends ZZIterable<T> {

    int size();

    boolean isEmpty();

    T remove() throws ZZEmptyContainerException;

    default void removeAll(){
        ZZIterator<T> it =this.getIterator();
        while( it.hasNext() ) {
            remove();
        }
    }
    default void removeAllExcept(ZZIterable<T> collection, ZZTest<T> tester){
        Iterator<T> it = (Iterator<T>) this.getIterator();
        while(it.hasNext()){
            if( tester.test(it.next()) )
                remove();
        }
    }

    void add(T e) throws ZZNoAvailableSpaceException;
    default void addAll(ZZIterable<T> collection) throws ZZNoAvailableSpaceException{
        ZZIterator<T> it = getIterator();
        while( it.hasNext() )
            add( it.getNext() );
    }
    default void addAllExcept(ZZIterable<T> collection, ZZTest<T> tester)throws ZZNoAvailableSpaceException{
        ZZIterator<T> it = getIterator();
        while( it.hasNext() ) {
            T elem = it.getNext();
            if (tester.test(elem)) {
                add(elem);
            }
        }
    }

}