package com.company.ZZNode;

public class ZZDoubleNode<T> extends ZZSimpleNode<T> {
    ZZDoubleNode prev;

    public ZZDoubleNode(T elem) {
        this(elem,null,null);

    }

    public ZZDoubleNode(T elem, ZZDoubleNode next, ZZDoubleNode prev) {
        super(elem, next);
        this.prev=prev;
        if(next!=null){
            next.setPrev(this);
        }
        if(prev!=null){
            prev.setNext(this);
        }
    }

    public ZZDoubleNode<T> getPrev(){ return this.prev; }

    public void setPrev(ZZDoubleNode<T> prev){ this.prev = prev; }

    @Override
    public ZZDoubleNode<T> getNext() {
        return (ZZDoubleNode<T>) super.getNext();
    }

    public void delete(){ //sconcatena
        if(prev!=null) {
            prev.setNext(next);
        }
        if(next!=null) {
            getNext().setPrev(prev);
        }
        prev=null;
        next=null;
    }
}
