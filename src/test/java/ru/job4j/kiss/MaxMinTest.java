package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
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
}