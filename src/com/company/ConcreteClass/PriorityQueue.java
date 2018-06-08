package com.company.ConcreteClass;

import com.company.Interfacce.*;
import com.company.ZZExceptions.ZZInvalidArgumentException;
import com.company.ZZExceptions.ZZNoAvailableSpaceException;
import com.company.ZZFunctions.ZZBFunction;
import com.company.ZZFunctions.ZZFold;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;
import com.company.ZZNode.PriorityNode;

public class PriorityQueue<T> implements ZZPriorityQueue<T> {

    private Lista<PriorityNode<T>> heap;
    private ZZFunction<T,Double> priorityFun;
    
    public PriorityQueue(){
        heap = new Lista<>();
        priorityFun=new ZZFunction<T, Double>() {
            @Override
            public Double apply(T e) {
                return 0.0;
            }
        };
    }

    public PriorityQueue(ZZFunction<T,Double> fun){
        heap = new Lista<PriorityNode<T>>();
        priorityFun = fun;
    }

    public PriorityQueue(ZZFunction<T,Double> fun, ZZIterable<T> elem) {
        this(fun);
        buildMaxHeap(elem);
    }
    
    public void setPriorityFun(ZZFunction<T,Double> fun){
        this.priorityFun=fun;
    }

    private void buildMaxHeap(ZZIterable<T> elems){
        ZZIterator<T> it = elems.getIterator();

        while( it.hasNext() ){
            T e = it.getNext();
            if(e == null)
                throw new ZZInvalidArgumentException("the collection contains null elements");
            else{
                PriorityNode<T> node = new PriorityNode<T>(e, priorityFun);
                heap.add(node);
            }
        }
        for(int i=heap.size()/2+1; i>0; i--){
            maxHeapify(i);
        }
    }

    private void swap(int i, int j) {
        PriorityNode<T> temp = heap.getAt(i);
        heap.setAt(i, heap.getAt(j));
        heap.setAt(j, temp);
    }

    private void maxHeapify(int i){
        int max;
        int left = i*2 + 1;
        int right = i*2 + 2;
        if( left < heap.size() && heap.getAt(i).getPriority() < heap.getAt(left).getPriority() )
            max = left;
        else
            max = i;
        if( right < heap.size() && heap.getAt(max).getPriority() < heap.getAt(right).getPriority() )
            max = right;
        if( i != max) {
            swap(i,max);
            maxHeapify(max);
        }
    }

    @Override
    public T getMax() {
        return heap.getAt(0).getElem();
    }

    @Override
    public T extractMax() {
        swap(0, heap.size()-1);
        T temp = heap.removeAt(heap.size()-1).getElem();
        maxHeapify(0);
        return temp;
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {

        Lista<T> l = new Lista<>();

        ZZFold< Lista<T>, PriorityNode<T>> f = (acc, elem) -> {
            if (tester.test(elem.getElem()))
                acc.insertTail(elem.getElem());
            return acc;
        };

        ZZIterator<PriorityNode<T>> it = heap.getIterator();
        while(it.hasNext()) {
            f.apply(l, it.getNext());
        }
        buildMaxHeap(l);
    }

    @Override
    public void add(T e) throws ZZNoAvailableSpaceException { //TODO: sistamre codice vedi asd
        heap.insertTail(new PriorityNode<>(e, priorityFun));
        swap(0, heap.size()-1);
        maxHeapify(0);
    }

    public PriorityQueue<T> increaseKey(int i, double value){ 
        int parent = i/2;
        heap.getAt(i).setPriorityValue(value);
        while(heap.getAt(parent).getPriority() < heap.getAt(i).getPriority() ){
            swap(i,parent);
            i = parent;
        }
        return this;
    }

    public <S> PriorityQueue<S> map(ZZFunction<T,S> mappingFun, ZZFunction<S,Double> priorityFun) {
        PriorityQueue<S> ris = new PriorityQueue<>(priorityFun);

        ZZIterator<T> it = getIterator();
        while (it.hasNext()) {
            ris.add(mappingFun.apply(it.getNext()));
        }
        return ris;
    }

    @Override
    public <S> PriorityQueue<S> map(ZZFunction<T,S> mappingFun) {
        return map(mappingFun, new ZZFunction<S, Double>() {
            @Override
            public Double apply(S e) {
                return 0.0;
            }
        })
    }    

    @Override
    public ZZIterator<T> getIterator(){
        Lista<T> ris=heap.map(new ZZFunction<PriorityNode<T>, T>() {
            @Override
            public T apply(PriorityNode<T> e) {
                return e.getElem();
            }
        });
        ris.sort(new ZZBFunction<Integer, T, T>() {
            @Override
            public Integer apply(T t, T t2) {
                return priorityFun.apply(t).compareTo(priorityFun.apply(t2));
            }
        });
        return ris.getIterator();
    }
}
