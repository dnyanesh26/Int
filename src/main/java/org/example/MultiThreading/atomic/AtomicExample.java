package org.example.MultiThreading.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        // 1. Basic increment
        int newValue = counter.incrementAndGet();
        System.out.println("After increment: " + newValue); // 1

        // 2. Add a specific value
        counter.addAndGet(10);
        System.out.println("After adding 10: " + counter.get()); // 11

        // 3. Compare and Set (The heart of atomic variables)
        // If the value is 11, change it to 50
        boolean isSuccess = counter.compareAndSet(11, 50);
        System.out.println("Update successful? " + isSuccess + ", Value: " + counter.get());

        // 4. Functional Update (Java 8+)
        // Multiply the current value by 2
        counter.updateAndGet(v -> v * 2);
        System.out.println("After functional update: " + counter.get()); // 100
    }
}
