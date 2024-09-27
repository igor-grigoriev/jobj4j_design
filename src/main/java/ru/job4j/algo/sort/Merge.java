package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int index = 0, indexL = 0, indexR = 0;
        int[] result = new int[left.length + right.length];
        while (indexL < left.length && indexR < right.length) {
            result[index++] = left[indexL] < right[indexR] ? left[indexL++] : right[indexR++];
        }
        while (index < result.length) {
            result[index++] = indexL != left.length ? left[indexL++] : right[indexR++];
        }
        return result;
    }
}