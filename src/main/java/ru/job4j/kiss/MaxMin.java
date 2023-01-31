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
        Iterator<? extends T> i = value.iterator();
        T candidate = i.next();
        while (i.hasNext()) {
            T next = i.next();
            if (predicate.test(comparator.compare(next, candidate))) {
                candidate = next;
            }
        }
        return candidate;
    }
}