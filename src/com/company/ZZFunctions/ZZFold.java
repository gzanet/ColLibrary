package com.company.ZZFunctions;

public interface ZZFold<T,E> {
    T apply(T acc, E elem);
}
