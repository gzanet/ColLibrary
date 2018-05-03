package com.company.ZZNode;

public class ZZDoubleNode<T> extends ZZSimpleNode {
    ZZDoubleNode prev;

    public ZZDoubleNode(T elem) {
        this(elem,null,null);

    }

    public ZZDoubleNode(T elem, ZZDoubleNode next) {
        this(elem, next,null);
    }

    public ZZDoubleNode(T elem, ZZDoubleNode next, ZZDoubleNode prev) {
        super(elem, next);
        this.prev=prev;
    }

}
