package com.company.ZZNode;

public class ZZDoubleNode<T>{
    private ZZDoubleNode prev;
    private ZZDoubleNode next;
    private T elem;

    public ZZDoubleNode(T elem) {
        this(elem,null,null);

    }

    public ZZDoubleNode(T elem, ZZDoubleNode next, ZZDoubleNode prev) {
        this.elem = elem;
        this.next = next;
        this.prev=prev;
        if(next!=null){
            next.setPrev(this);
        }
        if(prev!=null){
            prev.setNext(this);
        }
    }


    public T getElem() { return this.elem; }

    public void setElem(T data){ this.elem = data; }


    public ZZDoubleNode<T> getPrev(){ return this.prev; }
    public ZZDoubleNode<T> getNext(){ return this.next; }


    //rendere più consistenti
    public void setPrev(ZZDoubleNode<T> prev){ this.prev = prev;}
    public void setNext(ZZDoubleNode<T> next){ this.next = next;}


    public void sconcatena(){ //sconcatena
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
