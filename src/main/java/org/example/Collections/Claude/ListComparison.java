package org.example.Collections.Claude;

import java.util.*;

public class ListComparison {
    public static void main(String[] args) {
        // ArrayList: best for random access and iteration
        List<Integer> arrayList = new ArrayList<>(100); // pre-size!
        arrayList.add(10);
        arrayList.add(20);
        arrayList.get(1); // O(1) - direct index

        // Benchmark: middle insertion
        long start = System.nanoTime();
        for (int i = 0; i < 10_000; i++)
            arrayList.add(arrayList.size() / 2, i); // O(n) shift
        System.out.println("ArrayList mid-insert: " + (System.nanoTime()-start) + "ns");

        // LinkedList: best as Deque (stack/queue)
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1); // O(1)
        deque.addLast(2);  // O(1)
        deque.pollFirst(); // O(1)

        // ArrayDeque is better than LinkedList as a queue in most cases
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.push(1);
        arrayDeque.pop();

        // Rule of thumb:
        // - Frequent random access OR append-only -> ArrayList
        // - Frequent add/remove at BOTH ends      -> ArrayDeque
        // - Frequent mid-list insert/remove       -> LinkedList (but benchmark first)
    }
}

