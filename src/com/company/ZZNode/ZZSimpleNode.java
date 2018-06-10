package com.company.ZZNode;

import com.company.Interfacce.ZZIterable;
import com.company.Interfacce.ZZIterator;
import com.company.ZZExceptions.ZZEmptyContainerException;

public class ZZSimpleNode<T> implements ZZIterable {
    private T elem;
    private ZZSimpleNode<T> next;

    public ZZSimpleNode(T elem) {
        this(elem,null);
    }

    public ZZSimpleNode(T elem, ZZSimpleNode<T> next) {
        this.elem = elem;
        this.next = next;
    }

    public T getElem() { return this.elem; }

    public ZZSimpleNode<T> getNext(){ return this.next; }

    public void setElem(T data){ this.elem = data; }

    public void setNext(ZZSimpleNode<T> next){ this.next = next; }

    @Override
    public ZZIterator<T> getIterator() {
        ZZSimpleNode<T> t=this;
        return new ZZIterator<T>() {
            ZZSimpleNode<T> p=t;
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public T getNext() throws ZZEmptyContainerException {
                if(p==null){throw new ZZEmptyContainerException("Errore:container vuoto");}
                T ris=p.getElem();
                p=p.getNext();
                return ris;
            }
        };
    }

    public static <T> ZZIterator<T> getIterator(ZZSimpleNode<T> node){
        if(node==null) {
            return new ZZIterator<T>(){};
        } else {
            return node.getIterator();
        }
    }
}


