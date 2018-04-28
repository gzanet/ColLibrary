package com.company;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

public interface ZZCollection<T> extends ZZIterable<T> {

    int size();

    boolean isEmpty();

    T remove() throws ZZEmptyContainerException;

    default void removeAll(){
        while( !isEmpty() ) {
            remove();
        }
    }

    void removeAllExcept(ZZTest<T> tester);

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

    <E> ZZCollection<E> map(ZZFunction<T, E> function);

}