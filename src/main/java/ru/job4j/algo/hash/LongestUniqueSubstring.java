package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {

    public static String longestUniqueSubstring(String str) {
        String result = "";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < str.length(); j++) {
            char character = str.charAt(j);
            if (map.containsKey(character)) {
                i = Math.max(i, map.get(character) + 1);
            }
            if (result.length() <  j - i + 1) {
                result = str.substring(i, j + 1);
            }
            map.put(character, j);
        }
        return result;
    }

}