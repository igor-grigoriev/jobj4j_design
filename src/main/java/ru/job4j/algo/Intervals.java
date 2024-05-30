package ru.job4j.algo;

import java.util.Arrays;
import java.util.LinkedList;

public class Intervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i, j) -> {
            if (i[1] == j[1]) {
                return i[0] - j[0];
            }
            return i[1] - j[1];
        });
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] interval : intervals) {
            if (!list.isEmpty() && list.getLast()[1] >= interval[0]) {
                while (!list.isEmpty() && list.getLast()[1] >= interval[0]) {
                    interval[0] = Math.min(list.getLast()[0], interval[0]);
                    interval[1] = Math.max(list.getLast()[1], interval[1]);
                    list.removeLast();
                }
            }
            list.addLast(interval);
        }
        int[][] result = new int[list.size()][];
        int k = 0;
        for (int[] interval : list) {
            result[k++] = interval;
        }
        return  result;
    }

}