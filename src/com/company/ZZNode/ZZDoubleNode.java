package com.company.ZZNode;

public class ZZDoubleNode<T>{
    private ZZDoubleNode<T> prev;
    private ZZDoubleNode<T> next;
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

    public void setPrev(){
        if(prev!=null){
            prev.next=null;
            prev=null;
        }
    }

    //prev nodo singolo
    public void setPrev(ZZDoubleNode<T> x){
        if(x==null){
            setPrev();
        }
        else{
            ZZDoubleNode<T> temp=this.prev;
            x.sconcatena();

            x.next = this;
            this.prev=x;

            if(temp!=null) {
                temp.next = x;
            }
            x.prev=temp;
        }
    }

    public void setNext(){
        if(next!=null){
            next.prev=null;
            next=null;
        }
    }


    public void setNext(ZZDoubleNode<T> x){
        if(x==null){
            setNext();
        }
        else{
            ZZDoubleNode<T> temp=this.next;
            x.sconcatena();

            x.prev = this;
            this.next=x;

            if(temp!=null) {
                temp.prev = x;
            }
            x.next=temp;
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
