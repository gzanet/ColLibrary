package com.company.ConcreteClass;

import com.company.Interfacce.*;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZDoubleNode;

public class Lista<T> implements ZZList<T> {

    ZZDoubleNode<T> head;
    ZZDoubleNode<T> tail;
    int size;

   public Lista(){
       size=0;
       head=null;
       tail=null;
   }

   public Lista(ZZIterable iterable){
       this();
       addAll(iterable);
   }

   @Override
   public ZZList<T> insertHead(T elem){
       if(size==0){
           head=new ZZDoubleNode<>(elem);
           tail=head;
       }else{
           ZZDoubleNode<T> temp=new ZZDoubleNode<>(elem,head,null);
           head=temp;
       }
       size++;
       return this;
   }

   @Override
   public ZZList<T> insertTail(T elem){
       if(size==0){
           head=new ZZDoubleNode<>(elem);
           tail=head;
       }else{
           ZZDoubleNode<T> temp=new ZZDoubleNode<>(elem,null,tail);
           tail=temp;
       }
       size++;
       return this;
   }
    @Override
    public ZZList<T> insertAt(int position, T elem) {
       if(position<0){throw new ZZInvalidArgumentException("indece sbagliato");}
       if(position>=size-1){
           return insertTail(elem);
       }
       else if(position==0){
           return insertHead(elem);
       }
       else if(position<size/2){//siamo più vicini alla testa
           ZZDoubleNode<T> temp=head;
           for(int i=0;i<position;i++){
            temp=temp.getNext();
           }
           //temp è il precedente
            new ZZDoubleNode<T>(elem,temp.getNext(),temp);
       }
       else{
           ZZDoubleNode<T> temp=tail;
           for(int i=size-1;i>position;i--){
               temp=temp.getPrev();
           }
           //temp è il successivo
           new ZZDoubleNode<T>(elem,temp,temp.getPrev());
       }
       size++;
       return this;
    }

    @Override
    public ZZList<T> inserAt(int position, ZZCollection<T> col) {/*TODO brutta efficenza*/
        ZZIterator<T> it=col.getIterator();
        while(it.hasNext()){
            insertAt(position,it.getNext());
            position++;
        }
        return this;
    }

    @Override
    public void removeAll(){
       size=0;
       head=null;
       tail=null;
    }

    public T removeHead() throws ZZEmptyContainerException{
       if(size==0){
           throw new ZZEmptyContainerException("vuota");
       }
       else if(size==1){
           ZZDoubleNode<T> temp=head;
           size--;
           head=null;
           tail=null;
           return temp.getElem();

       }
       else{
           ZZDoubleNode<T> temp=head;
           head=head.getNext();
           temp.sconcatena();
           size--;
           return temp.getElem();
       }
   }
    public T removeTail() throws ZZEmptyContainerException{
        if(size==0){
            throw new ZZEmptyContainerException("vuota");
        }
        else if(size==1){
            ZZDoubleNode<T> temp=head;
            size--;
            head=null;
            tail=null;
            return temp.getElem();

        }
        else{
            ZZDoubleNode<T> temp=tail;
            tail=tail.getPrev();
            temp.sconcatena();
            size--;
            return temp.getElem();
        }
    }

    @Override
    public T removeAt(int position) throws ZZEmptyContainerException {
       //controlli testa,coda, input
        if(position<0 || position>=size){throw new ZZEmptyContainerException("indici  sbagliati");}
        else if(position==0){
            return removeHead();
        }
        else if(position==size-1){
            return removeTail();
        }
        else {
            ZZDoubleNode<T> temp;
            if (position < size / 2) {//siamo più vicini alla testa
                temp = head;
                for (int i = 0; i < position; i++) {
                    temp = temp.getNext();
                }
            } else {
                temp = tail;
                for (int i = size - 1; i > position; i--) {
                    temp = temp.getPrev();
                }
            }
            temp.sconcatena();
            size--;
            return temp.getElem();
        }
    }

    //fare senza ricreazione nodi
    @Override
    public ZZList<T> removeFrom(int position) {
        Lista<T> temp=new Lista<T>();
        while(position<size){
            temp.insertHead(removeTail());
        }
        return temp;
    }

    @Override
    public ZZList<T> removeUntil(int position) {
        /*Lista<T> temp=new Lista<T>();
        while(position<size){
            temp.insertTail(removeHead());
        }
        return temp;*/
        Lista<T> temp=(Lista)removeFrom(position);
        swap(temp);
        return temp;
    }

    @Override
    public T getAt(int position) throws ZZNotFoundException {
       if(size==0){throw new ZZEmptyContainerException("Lista vuota");
       }
       else if(position<0 || position>=size){throw new ZZInvalidArgumentException("posizione non valida"); }
       else{
           ZZDoubleNode<T> temp;
           if (position < size / 2) {//siamo più vicini alla testa
               temp = head;
               for (int i = 0; i < position; i++) {
                   temp = temp.getNext();
               }
           } else {
               temp = tail;
               for (int i = size - 1; i > position; i--) {
                   temp = temp.getPrev();
               }
           }
           return temp.getElem();
       }

    }

    @Override
    public int indexOf(T elem) {
       int position=0;
       ZZDoubleNode<T> temp=head;
       boolean trovato=false;
       while(position<size && !trovato){
           trovato=temp.getElem().equals(elem);
           position++;
           temp=temp.getNext();
       }
       return trovato?position-1:-1;

    }

    @Override
    public int lastIndeOf(T elem) {
        int position=size-1;
        ZZDoubleNode<T> temp=tail;
        boolean trovato=false;
        while(position>0 && !trovato){
            trovato=temp.getElem().equals(elem);
            position--;
            temp=temp.getPrev();
        }
        return trovato?position+1:-1;
    }

    //tipi comparable
    @Override
    public void sort() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {
        boolean testa=false;
        ZZDoubleNode<T> temp=head;
        while(!testa && temp!=null){
            if(tester.test(temp.getElem())){
                testa=true;
                temp=temp.getNext();
            }
            else{
                removeHead();
                temp=head;
            }
        }
        while(temp!=null && temp!=tail){
            if(!tester.test(temp.getElem())){
                temp=temp.getNext();
                temp.getPrev().sconcatena();
            }
            else {
                temp = temp.getNext();
            }
        }
        if(tail!=null && !tester.test(tail.getElem())){
            removeTail();
        }

    }

    @Override
    public <S> ZZCollection<S> map(ZZFunction<T, S> fun) {
        Lista<S> ris= new Lista<S>();
        ZZDoubleNode<T> temp=head;
        for(int i=0;i<size;i++){
            ris.insertTail(fun.apply(temp.getElem()));
            temp=temp.getNext();
        }
        return ris;
    }

    @Override
    public ZZIterator<T> getIterator() {
        return new ZZIterator<T>() {
            ZZDoubleNode<T> temp=head;
            @Override
            public boolean hasNext() {
                return temp!=null;
            }

            @Override
            public T getNext() throws ZZEmptyContainerException {
                if(temp==null){throw  new ZZEmptyContainerException("vuoto");}
                T elem=temp.getElem();
                temp=temp.getNext();
                return elem;
            }
        };
    }

    private void swap(Lista<T> list){
        ZZDoubleNode<T> node=head;
        head=list.head;
        list.head=node;

        node=tail;
        tail=list.tail;
        list.tail=node;

        int s=size;
        size=list.size;
        list.size=s;
    }
}
