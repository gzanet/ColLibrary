package com.company.Interfacce;

import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZFold;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

import java.util.Iterator;

public interface ZZCollection<T> extends ZZIterable<T> {

    int size();

    default boolean isEmpty(){
        return size()==0;
    }

    T remove() throws ZZEmptyContainerException;

    default void removeAll(){
        while(!isEmpty()){
            remove();
        }
    }
    void removeAllExcept(ZZTest<T> tester);

    ZZCollection<T> add(T e) throws ZZNoAvailableSpaceException;

    default void addAll(ZZIterable<T> collection) throws ZZNoAvailableSpaceException{

        addAllExcept(collection, new ZZTest<T>() {
            @Override
            public boolean test(T e) {
                return true;
            }
        });
       /* ZZIterator<T> it = collection.getIterator();
        while( it.hasNext() )
            add( it.getNext() );*/
    }
    default void addAllExcept(ZZIterable<T> collection, ZZTest<T> tester)throws ZZNoAvailableSpaceException{
        ZZIterator<T> it = collection.getIterator();
        while( it.hasNext() ) {
            T elem = it.getNext();
            if (tester.test(elem)) {
                add(elem);
            }
        }
    }

    <S> ZZCollection<S> map(ZZFunction<T,S> fun);

    default <S> S fold(S acc, ZZFold<S,T> fun){
        ZZIterator<T> it = getIterator();
        while(it.hasNext()){
            acc=fun.apply(acc,it.getNext());
        }
        return acc;
    }

}