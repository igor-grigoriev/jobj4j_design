package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T value;
        try {
            value = out.pop();
        } catch (NoSuchElementException e) {
            while (true) {
                try {
                    out.push(in.pop());
                } catch (NoSuchElementException t) {
                    value = out.pop();
                    break;
                }
            }
        }
        return value;
    }

    public void push(T value) {
        in.push(value);
    }
}