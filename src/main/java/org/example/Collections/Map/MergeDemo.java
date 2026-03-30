package org.example.Collections.Map;

import java.util.concurrent.ConcurrentHashMap;

public class MergeDemo {

    public static void main(String[] args) {
        String[] words = {"a","b","a","c","b"};

        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();

        for(String word : words){
            map.merge(word,1,Integer::sum);
        }

        System.out.println(map);
    }
}
