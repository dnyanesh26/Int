package org.example.DSA.GPT;

import java.util.*;

public class FrequentWords {
    public static String nthFrequent(String str, int n) {
        String[] words = str.split(" ");
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        return list.get(n - 1).getKey();
    }

    public static void main(String[] args) {
        String str = "apple banana apple orange banana apple";
        System.out.println(nthFrequent(str, 1)); // apple
    }
}