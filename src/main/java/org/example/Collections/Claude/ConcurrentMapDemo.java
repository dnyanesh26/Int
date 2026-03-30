package org.example.Collections.Claude;

import java.util.concurrent.*;
import java.util.*;

public class ConcurrentMapDemo {
    public static void main(String[] args) throws InterruptedException {

        // ConcurrentHashMap - fine-grained locking
        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();

        // Atomic operations - no external sync needed
        wordCount.put("hello", 0);
        wordCount.merge("hello", 1, Integer::sum);        // atomic increment
        wordCount.compute("hello", (k, v) -> v == null ? 1 : v + 1); // atomic compute
        wordCount.computeIfAbsent("world", String::length);       // atomic put-if-absent
        System.out.println(wordCount);
        // WRONG: non-atomic check-then-act
        // if (!map.containsKey(k)) map.put(k, v); // race condition!
        // CORRECT:
        wordCount.putIfAbsent("java", 1); // atomic

        // Parallel bulk operations (Java 8+)
        wordCount.forEach(1, (k, v) ->  // parallelismThreshold=1 -> always parallel
                System.out.println(Thread.currentThread().getName() + ": " + k + "=" + v)
        );

        long sum = wordCount.reduceValues(1, v -> (long) v, Long::sum);
        System.out.println("Total count: " + sum);

        // ConcurrentSkipListMap - sorted + concurrent
        ConcurrentSkipListMap<Integer, String> skipMap = new ConcurrentSkipListMap<>();
        skipMap.put(3, "three"); skipMap.put(1, "one"); skipMap.put(2, "two");
        System.out.println(skipMap.firstKey()); // 1 - concurrent + sorted

        // CopyOnWriteArrayList - best for read-heavy, write-rare scenarios
        List<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("a"); cowList.add("b");
        // Safe to iterate while another thread modifies - iterates snapshot
        for (String s : cowList) {
            cowList.add("c"); // won't throw ConcurrentModificationException
            System.out.println(s);
        }
        System.out.println(cowList);
    }
}
