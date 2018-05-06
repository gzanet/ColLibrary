package com.company.ZZNode;

public class ZZDoubleNode<T> extends ZZSimpleNode<T> {
    ZZDoubleNode prev;

    public ZZDoubleNode(T elem) {
        this(elem,null,null);

    }

    public ZZDoubleNode(T elem, ZZDoubleNode next, ZZDoubleNode prev) {
        super(elem, next);
        this.prev=prev;
    }

    public ZZDoubleNode<T> getPrev(){ return this.prev; }

    public void setPrev(ZZDoubleNode<T> prev){ this.prev = prev; }


}
