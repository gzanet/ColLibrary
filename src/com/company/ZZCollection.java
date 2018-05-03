package com.company;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

import java.util.Iterator;

public interface ZZCollection<T> extends ZZIterable<T> {

    int size();

    boolean isEmpty();

    //T remove(Object o) throws ZZEmptyContainerException;
    /*default void removeAll(){
        Iterator<T> it = (Iterator<T>) getIterator();
        while( it.hasNext() ) {
            remove(it.next());
        }
    }*/
    /*default void removeAllExcept(ZZIterable<T> collection, ZZTest<T> tester) throws ZZInvalidArgumentException {
        Iterator<T> it = (Iterator<T>) this.getIterator();
        while(it.hasNext()){
            T e = it.next();
            if( tester.test(e) )
                remove(e);
        }
    }*/

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