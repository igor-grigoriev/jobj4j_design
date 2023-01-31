package ru.job4j.kiss;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Iterator<? extends T> i = value.iterator();
        T candidate = i.next();
        while (i.hasNext()) {
            T next = i.next();
            if (comparator.compare(next, candidate) > 0) {
                candidate = next;
            }
        }
        return candidate;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Iterator<? extends T> i = value.iterator();
        T candidate = i.next();
        while (i.hasNext()) {
            T next = i.next();
            if (comparator.compare(next, candidate) < 0) {
                candidate = next;
            }
        }
        return candidate;
    }
}