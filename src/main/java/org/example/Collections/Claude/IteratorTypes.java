package org.example.Collections.Claude;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class IteratorTypes {
    public static void main(String[] args) {

        // --- Fail-Fast ---
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        try {
            for (Integer i : list) {
                if (i == 2) list.remove(i); // structural modification!
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Fail-fast: " + e.getClass().getSimpleName());
        }
        list.add(2);

        // CORRECT: use Iterator.remove()
        Iterator<Integer> it = list.iterator();
        int j=1;
        while (it.hasNext()) {
            System.out.println(j++);
            if (it.next() == 3){
                it.remove();
                // safe, doesn't increment modCount
            }
        }

        System.out.println(list);
        // Or use removeIf (Java 8+) - internally uses iterator
        list.removeIf(i -> i % 2 == 0);

        // --- Fail-Safe ---
        List<String> cowList = new CopyOnWriteArrayList<>(Arrays.asList("a","b","c"));
        for (String s : cowList) {
            cowList.add("d"); // no exception, but iterator won't see 'd'
        }
        System.out.println(cowList); // [a, b, c, d, d, d] (3 adds)

        // ConcurrentHashMap uses weakly-consistent iterator
        Map<String, Integer> chm = new java.util.concurrent.ConcurrentHashMap<>();
        chm.put("x", 1); chm.put("y", 2);
        for (Map.Entry<String, Integer> entry : chm.entrySet()) {
            chm.put("z", 3); // safe, no exception, 'z' may or may not appear
            System.out.println(chm);
        }

    }
}

