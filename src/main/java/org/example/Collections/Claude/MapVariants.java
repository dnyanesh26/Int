package org.example.Collections.Claude;

import java.util.*;

public class MapVariants {
    public static void main(String[] args) {

        // LinkedHashMap - insertion order (default)
        Map<String, Integer> linked = new LinkedHashMap<>();
        linked.put("banana", 2); linked.put("apple", 1); linked.put("cherry", 3);
        System.out.println(linked); // {banana=2, apple=1, cherry=3}

        // LinkedHashMap - LRU cache (access-order, removeEldestEntry)
        Map<String, String> lru = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3; // max 3 entries
            }
        };
        lru.put("a","A"); lru.put("b","B"); lru.put("c","C");
        System.out.println(lru.keySet()); // [a, b, c]

        lru.get("a"); // 'a' moved to end (recently used)
        System.out.println(lru.keySet()); // [b, c, a]

        lru.put("d","D"); // evicts 'b' (least recently used)
        System.out.println(lru.keySet()); // [c, a, d]

        // TreeMap - sorted keys, NavigableMap ops
        TreeMap<Integer, String> tree = new TreeMap<>();
        tree.put(5, "five"); tree.put(2, "two"); tree.put(8, "eight");
        System.out.println(tree.firstKey());         // 2
        System.out.println(tree.floorKey(6));        // 5
        System.out.println(tree.ceilingKey(6));      // 8
        System.out.println(tree.subMap(2, 8));       // {2=two, 5=five}
        System.out.println(tree.headMap(5));         // {2=two}

        // TreeMap with custom comparator (reverse order)
        TreeMap<String, Integer> reversed = new TreeMap<>(Comparator.reverseOrder());
        reversed.put("a", 1); reversed.put("c", 3); reversed.put("b", 2);
        System.out.println(reversed.keySet()); // [c, b, a]
    }
}

