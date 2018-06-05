package com.company.ConcreteClass;

import com.company.Interfacce.ZZIterator;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZNode.ZZDoubleNode;

public class ListaIterator<T> implements ZZIterator<T> {
    private int position;
    private Lista<T> enclosing;

    public ListaIterator(Lista<T> l){
        enclosing = l;
        position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < enclosing.size();
    }

    @Override
    public T getNext() throws ZZEmptyContainerException {
        position++;
        return enclosing.getAt(position);
    }
}
