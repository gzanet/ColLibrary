package com.company.Interfacce;

import com.company.ConcreteClass.HashTable;
import com.company.ZZFunctions.ZZBiFunction;
import com.company.ZZFunctions.ZZFunction;

public interface ZZHashTable<T> extends ZZCollection<T>{

    boolean containsValue(T elem);
    HashTable<T> delete(T elem);
    void addHashFunction(ZZFunction<T, Integer> fun);

    <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun);
    <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun, ZZFunction<S,Integer> hashFun);
    <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun, ZZBiFunction<Double,S,S> comparator);
    <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun, ZZFunction<S,Integer> hashFun, ZZBiFunction<Double,S,S> comparator);
}


