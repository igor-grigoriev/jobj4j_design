package ru.job4j.kiss;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return iterate(value, comparator, comp -> comp > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return iterate(value, comparator, comp -> comp < 0);
    }

    private <T> T iterate(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T result = null;
        if (value != null && !value.isEmpty() && comparator != null) {
            Iterator<? extends T> i = value.iterator();
            result = i.next();
            while (i.hasNext()) {
                T next = i.next();
                if (predicate.test(comparator.compare(next, result))) {
                    result = next;
                }
            }
        }
        return result;
    }
}