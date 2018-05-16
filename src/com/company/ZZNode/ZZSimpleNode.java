package com.company.ZZNode;

public class ZZSimpleNode<T> {
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
}
