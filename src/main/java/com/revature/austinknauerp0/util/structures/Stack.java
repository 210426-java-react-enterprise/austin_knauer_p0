package com.revature.austinknauerp0.util.structures;

public class Stack<T> implements Stackable<T>, List<T> {

    private T currentValue;
    private Stack<T> underneath;

    public Stack() {
        this.currentValue = null;
    }

    public Stack(T currentValue) {
        this.currentValue = currentValue;
    }

    public Stack(T currentValue, Stack<T> underneath) {
        this.currentValue = currentValue;
        this.underneath = underneath;
    }

    public void add(T object) {
        this.underneath = new Stack<T>(this.currentValue, this.underneath);
        this.currentValue = object;
    }

    public T pop() throws Exception {
        if (this.isEmpty())
            throw new Exception("Stack is empty");
        T temp = this.currentValue;
        this.currentValue = this.underneath.currentValue;
        this.underneath = this.underneath.underneath;
        return temp;
    }

    public T peek() {
        return this.currentValue;
    }

    public int size() {
        int inc = 0;
        Stack<T> current = this;
        while (current.currentValue != null) {
            inc++;
            current = current.underneath;
        }

        return inc;
    }

    public boolean isEmpty() {
        return this.currentValue == null;
    }

}
