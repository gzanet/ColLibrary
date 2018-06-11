package com.company.ConcreteClass;

import com.company.Interfacce.ZZHashTable;
import com.company.Interfacce.ZZIterable;
import com.company.Interfacce.ZZIterator;
import com.company.ZZExceptions.ZZEmptyContainerException;
import com.company.ZZExceptions.ZZNotFoundException;
import com.company.ZZFunctions.ZZBiFunction;
import com.company.ZZFunctions.ZZFunction;
import com.company.ZZFunctions.ZZTest;

public class HashTable<T> implements ZZHashTable<T> {
    private final int TABLESIZE;
    private final Lista<ZZFunction<T,Integer>> hashFunctions;
    private ZZBiFunction<Double,T,T> comparator;
    private Lista<T> table[];
    private int size;
    private ZZFunction<T,Integer> hash;

    public HashTable(int tableSize){
        this.TABLESIZE = tableSize;
        hash = null;
        comparator = null;
        hashFunctions = new Lista<>();
        table = new Lista[tableSize];
        for(int i=0; i<tableSize; i++){
            table[i] = new Lista<>();
        }
    }
    public HashTable(int tableSize, ZZBiFunction<Double,T,T> comparator){
        this(tableSize);
        this.comparator = comparator;
    }
    public HashTable(int tableSize, ZZBiFunction<Double,T,T> comparator, ZZFunction<T,Integer> funHash){
        this(tableSize, comparator);
        hashFunctions.insertHead(funHash);
        hash = hashFunctions.getAt(0);
    }
    private HashTable(int tableSize, ZZBiFunction<Double,T,T> comparator, ZZFunction<T,Integer> funHash, ZZIterable<T> elems){ //TODO: verificare wildcard
        this(tableSize, comparator, funHash);
        ZZIterator<T> it = elems.getIterator();
        while(it.hasNext()) {
            insert(it.getNext());
        }
    }
    //è necessario mantenerlo privato, perchè altrimenti in costruzione il bilanciamento potrebbe non essere garantito

    @Override
    public void add(T e){
        int key = hash.apply(e);
        table[key].insertHead(e);
        size++;
        balanceTable();
    }

    private void balanceTable() {
        ZZFunction<T, Integer> fun = hash;
        int minLength = this.findMaxLength();

        for (int i = 0; i < hashFunctions.size(); i++) {
            HashTable<T> t = new HashTable<>(TABLESIZE, comparator, hashFunctions.getAt(i), this);
            if(t.findMaxLength() < minLength) {
                minLength = t.findMaxLength();
                fun = hashFunctions.getAt(i);
            }
        }
        if(fun != hash) {
            hash = fun;
            fillTable();
        }
    }
    private void insert(T e){
        int key = hash.apply(e);
        table[key].insertHead(e);
        size++;
    }
    private void fillTable() {
        ZZIterator<T> it = this.getIterator();
        for(int i = 0; i< TABLESIZE; i++){
            table[i] = new Lista<T>();
        }
        while (it.hasNext()) {
            insert(it.getNext());
        }
    }
    private int findMaxLength(){
        int max = 0;
        for(int i = 0; i< TABLESIZE; i++){
            if(table[i].size() > max)
                max = table[i].size();
        }
        return max;
    }

    @Override
    public boolean containsValue(T elem){
        int key = hash.apply(elem);
        ZZIterator<T> it = table[key].getIterator();
        while(it.hasNext()) {
            if (comparator.apply(elem, it.getNext()) == 0)
                return true;
        }
        return false;
    }

    public void addHashFunction (ZZFunction <T, Integer > fun){
        hashFunctions.insertHead(fun);
        balanceTable();
    }

    public HashTable<T> delete(T elem){
        if(containsValue(elem) == false) throw new ZZNotFoundException("element not present");
        int key = hash.apply(elem);
        table[key].removeAllExcept(e -> comparator.apply(e,elem) != 0); //removeAllExcept è quello di Lista
        balanceTable();
        return this;
    }

    public ZZIterator<T> getIterator() {

        Lista<T> l = new Lista<>();

        for(int i = 0; i< TABLESIZE; i++) {
            ZZIterator<T> it = table[i].getIterator();
            while(it.hasNext()){
                l.insertTail(it.getNext());
            }
        }
        return l.getIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove() throws ZZEmptyContainerException{
        ZZIterator<T> it = this.getIterator();
        if (it.hasNext()) {
            T elem = it.getNext();
            delete(elem);
            return elem;
        }
        throw new ZZNotFoundException("element not contained");
        //bisogna chiamare il metodo delete poichè eliminare l'elemento dalla lista it
        //non lo rimuove effettivamente rimane puntatore tabella
    }

    @Override
    public void removeAllExcept(ZZTest<T> tester) {
        ZZIterator<T> it = this.getIterator();
        for(int i=0; i<TABLESIZE; i++){
            table[i].removeAllExcept(tester); // removeAllExcept è di Lista
        }
    }

    private Lista<T> toList(){
        ZZIterator<T> it =this.getIterator();
        Lista<T> l = new Lista<>();
        while(it.hasNext())
            l.insertTail(it.getNext());
        return l;
    }

    @Override
    public <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun) {
        Lista<T> l = toList();
        HashTable<S> t = new HashTable<S>(TABLESIZE, null, null, l.map(e->mappingFun.apply(e)) );  //sfrutto metodo map di Lista
        return t;
    }
    public <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun, ZZFunction<S,Integer> hashFun){
        Lista<T> l = toList();
        HashTable<S> t = new HashTable<S>(TABLESIZE, null, hashFun, l.map(e->mappingFun.apply(e)) );
        return t;
    }
    public <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun, ZZBiFunction<Double,S,S> comparator){
        Lista<T> l = toList();
        HashTable<S> t = new HashTable<S>(TABLESIZE, comparator, null, l.map(e->mappingFun.apply(e)) );
        return t;
    }
    public <S> ZZHashTable<S> map(ZZFunction<T,S> mappingFun, ZZFunction<S,Integer> hashFun, ZZBiFunction<Double,S,S> comparator){
       Lista<T> l = toList();
        HashTable<S> t = new HashTable<>(TABLESIZE, comparator, hashFun, l.map(e->mappingFun.apply(e)) );  //sfrutto metodo map di Lista
        return t;
    }
}
