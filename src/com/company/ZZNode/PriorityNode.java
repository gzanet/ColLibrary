package com.company.ZZNode;

import com.company.ZZFunctions.ZZFunction;
public class PriorityNode<T> {
    private double priorityValue;
    private T elem;
    
    public PriorityNode(T elem, ZZFunction<T,Double> fun){
        this.elem = elem;
        this.priorityValue = fun.apply(this.elem);
    }
    public PriorityNode(T elem, double priority){
        this.elem = elem;
        this.priorityValue = priority;
    }

    public void setPriorityValue(double priorityValue) {
        if(priorityValue < this.priorityValue)
            this.priorityValue = priorityValue;
    }

    public double  getPriority() {
        return priorityValue;
    }
    
    public T getElem() {
        return elem;
    }

    @Override
    public String toString(){
        return String.valueOf(this.elem);
    }


}