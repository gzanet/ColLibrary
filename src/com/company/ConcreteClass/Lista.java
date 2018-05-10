package com.company.ConcreteClass;

import com.company.Interfacce.ZZCollection;
import com.company.Interfacce.ZZIterable;
import com.company.Interfacce.ZZIterator;
import com.company.Interfacce.ZZList;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.ZZDoubleNode;
import com.company.ZZNode.ZZSimpleNode;

import java.util.Iterator;

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
           ZZDoubleNode<T> temp=new ZZDoubleNode<>(elem,tail,null);
           tail=temp;
       }
       size++;
       return this;
   }
    @Override
    public ZZList<T> insertAt(int position, T elem) {
       if(position<0){throw new ZZInvalidArgumentException("indece sbagliato");}
       if(position>=size){
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
           temp.delete();
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
            temp.delete();
            size--;
            return temp.getElem();
        }
    }

    @Override
    public T removeAt(int position) throws ZZEmptyContainerException {
       //controlli testa,coda, input
        ZZDoubleNode<T> temp;
        if(position<size/2){//siamo più vicini alla testa
            temp=head;
            for(int i=0;i<position;i++){
                temp=temp.getNext();
            }
        }
        else{
            temp=tail;
            for(int i=size-1;i>position;i--){
                temp=temp.getPrev();
            }
        }
        temp.delete();
        size--;
        return temp.getElem();

    }

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
        Lista<T> temp=new Lista<T>();
        while(position<size){
            temp.insertTail(removeHead());
        }
        return temp;
    }

    @Override
    public T getAt(int position) throws ZZNotFoundException {
        return null;
    }

    @Override
    public int indexOf(T elem) {
        return 0;
    }

    @Override
    public int lastIndeOf(T elem) {
        return 0;
    }

    @Override
    public void sort() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {

    }

    @Override
    public <S> ZZCollection<S> map(ZZFunction<T, S> fun) {
        return null;
    }

    @Override
    public ZZIterator<T> getIterator() {
        return null;
    }
}
