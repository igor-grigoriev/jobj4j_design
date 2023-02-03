package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    @Test
    void max() {
        String result = new MaxMin().max(List.of("б", "г", "в", "а"), Comparator.naturalOrder());
        String expected = "г";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void min() {
        int result = new MaxMin().max(List.of(3, 2, 1, 4), Comparator.reverseOrder());
        int expected = 1;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenValueIsNull() {
        Object result = new MaxMin().max(null, Comparator.nullsFirst(null));
        Object expected = null;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenValueIsEmpty() {
        Integer result = new MaxMin().min(new ArrayList<Integer>(), Comparator.reverseOrder());
        Integer expected = null;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenComparatorIsNull() {
        String result = new MaxMin().max(List.of("б", "г", "в", "а"), null);
        String expected = null;
        assertThat(result).isEqualTo(expected);
    }
}