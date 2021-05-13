package com.revature.austinknauerp0.util.structures;

public class ArrayList<T> implements List<T>, Traversable<T>{

    private T[] container;
    private int initial;
    private int size;

    public ArrayList() {
        this.container = (T[]) new Object[10];
        this.initial = 10;
        this.size = 10;
    }

    public void add(T object) {
        int start = 0;
        while(container[start] != null) {
            start++;
        }
        if (start <= (this.size * .75)) {
            container[start] = object;
        } else if (start < size) {
            container[start] = object;
            this.grow();
        } else {
            this.grow();
            this.add(object);
        }
    }

    public void grow() {
        int newSize = this.size * 2;
        T[] newContainer = (T[]) new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            if (container[i] != null) {
                newContainer[i] = container[i];
            }
        }

        this.size = newSize;
        this.container = newContainer;
    }

    public void shrink() {
        int newSize = this.size / 2;
        T[] newContainer = (T[]) new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            if (container[i] != null) {
                newContainer[i] = container[i];
            }
        }

        this.size = newSize;
        this.container = newContainer;
    }

    public int size() {
        int count = 0;
        while (this.container[count] != null) {
            count++;
        }

        return count;
    }

    public T pop() throws Exception {
        if(this.container[0] == null) {
            throw new Exception("List is empty");
        }

        int finalValue = this.size - 1;

        T temp = container[0];
        for (int i = 0; i < this.size; i++) {
            if (container[i] == null) {
                finalValue = i;
                i = this.size;
            } else if (i == this.size - 1) {
                this.container[i] = null;
            } else {
                this.container[i] = this.container[i + 1];
            }
        }

        if (finalValue < (this.size / 2)  - 1) {
           this.shrink();
        }

        return temp;
    }

    public T remove(int index) throws Exception{
        // not sure if it would be better to just return null here
        if (this.container[index] == null) {

            throw new Exception("Null value at index.");

        } else {

            T temp = this.container[index];
            int finalValue = this.size - 1;

            for (int i = index; i < this.size; i++) {
                if (container[i] == null) {
                    finalValue = i;
                    i = this.size;
                } else if (i == this.size - 1) {
                    this.container[i] = null;
                } else {
                    this.container[i] = this.container[i + 1];
                }

            }

            if (finalValue < (this.size / 2)  - 1) {
                this.shrink();
            }

            return temp;
        }
    }

    public int find(T object) {
        for (int i = 0; i < this.size; i++) {
            if (this.container[i].equals(object))
                return i;
        }
        return -1;
    }

    public T get(int index) {
        return this.container[index];
    }
}
