package com.revature.austinknauerp0.util.structures;

public interface Traversable<T>{

    public T remove(int index) throws Exception;

    public int find(T object);

    public T get(int index);
}
