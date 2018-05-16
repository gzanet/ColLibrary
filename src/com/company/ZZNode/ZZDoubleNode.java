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
        setNext(next);
        setPrev(prev);
    }


    public T getElem() { return this.elem; }

    public void setElem(T data){ this.elem = data; }


    public ZZDoubleNode<T> getPrev(){ return this.prev; }
    public ZZDoubleNode<T> getNext(){ return this.next; }


    public void setPrev(ZZDoubleNode<T> prev){
        this.prev = prev;
        if(prev!=null) {
            prev.next = this;
        }
    }
    public void setNext(ZZDoubleNode<T> next){
        this.next = next;
        if(next!=null) {
            next.prev = this;
        }
    }


    public void sconcatena(){ //sconcatena
        if(prev!=null) {
            prev.setNext(next);
        }
        if(next!=null) {
            next.setPrev(prev);
        }
        prev=null;
        next=null;
    }

    public static <T> void swap(ZZDoubleNode<T> node1, ZZDoubleNode<T> node2){
        node1.swap(node2);
    }

    //scambi solo valori, scambiare tutti i nodi?
    public void swap(ZZDoubleNode<T> node){
        T temp=node.elem;
        node.elem=elem;
        elem=temp;
    }
}
