package org.example.MultiThreading.Claude;

import java.util.concurrent.*;
import java.util.*;

public class ConcurrentCollectionsDemo {

    public static void main(String[] args) throws Exception {

        // 1. ConcurrentHashMap — segment/bucket-level locking, never locks whole map
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("a", 1);

        // Atomic compound operations (no external sync needed)
        map.putIfAbsent("b", 2);
        map.computeIfAbsent("c", k -> k.length());
        map.compute("a", (k, v) -> v == null ? 1 : v + 1); // Atomic update
        map.merge("a", 1, Integer::sum); // Atomic merge

        // 2. CopyOnWriteArrayList — writes copy the array; reads are lock-free
        // Best for: infrequent writes, frequent reads (e.g., listener lists)
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        cowList.add("listener1");
        for (String s : cowList) { // Iteration never throws ConcurrentModificationException
            cowList.add("new"); // Safe! Iterator uses snapshot
        }

        // 3. BlockingQueue — producer-consumer pattern
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        // Producer
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    queue.put("item-" + i); // Blocks if queue is full
                    System.out.println("Produced: item-" + i);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }).start();

        // Consumer
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String item = queue.take(); // Blocks if queue is empty
                    System.out.println("Consumed: " + item);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }).start();

        // 4. PriorityBlockingQueue — unbounded priority queue
        PriorityBlockingQueue<Integer> pq = new PriorityBlockingQueue<>();
        pq.addAll(Arrays.asList(5, 2, 8, 1));
        System.out.println(pq.take()); // Returns 1 (lowest)

        // 5. ConcurrentLinkedQueue — non-blocking, lock-free (CAS-based) queue
        ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();
        clq.offer("a");
        clq.poll(); // Returns "a"

        // 6. Deque variants
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
        deque.offerFirst("front");
        deque.offerLast("back");

        Thread.sleep(500);
    }
}
