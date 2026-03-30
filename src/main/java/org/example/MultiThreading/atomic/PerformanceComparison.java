package org.example.MultiThreading.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class PerformanceComparison {
    private static final int NUM_THREADS = 4;
    private static final int INCREMENTS_PER_THREAD = 2_500_000;

    // 1. Synchronized Counter
    static class SyncCounter {
        private int count = 0;
        public synchronized void increment() { count++; }
        public int getCount() { return count; }
    }

    // 2. Atomic Counter
    static class AtomicCounter {
        private AtomicInteger count = new AtomicInteger(0);
        public void increment() { count.incrementAndGet(); }
        public int getCount() { return count.get(); }
    }

    public static void main(String[] args) throws InterruptedException {
        // Test Synchronized
        SyncCounter sync = new SyncCounter();
        long startSync = System.currentTimeMillis();
        runTest(sync::increment);
        long endSync = System.currentTimeMillis();
        System.out.println("Synchronized Time: " + (endSync - startSync) + "ms | Count: " + sync.getCount());

        // Test Atomic
        AtomicCounter atomic = new AtomicCounter();
        long startAtomic = System.currentTimeMillis();
        runTest(atomic::increment);
        long endAtomic = System.currentTimeMillis();
        System.out.println("Atomic Time: " + (endAtomic - startAtomic) + "ms | Count: " + atomic.getCount());
    }

    private static void runTest(Runnable task) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) task.run();
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
    }
}
