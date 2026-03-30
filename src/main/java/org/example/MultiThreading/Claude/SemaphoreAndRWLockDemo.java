package org.example.MultiThreading.Claude;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.*;

public class SemaphoreAndRWLockDemo {

    // Semaphore: limit concurrent access (e.g., connection pool)
    static class ConnectionPool {
        private final Semaphore semaphore;
        private final Queue<String> connections = new ConcurrentLinkedQueue<>();

        ConnectionPool(int size) {
            semaphore = new Semaphore(size, true); // fair semaphore
            for (int i = 0; i < size; i++) connections.add("Connection-" + i);
        }

        public String acquire() throws InterruptedException {
            semaphore.acquire(); // Blocks if no permits available
            return connections.poll();
        }

        public void release(String conn) {
            connections.offer(conn);
            semaphore.release(); // Returns permit
        }
    }

    // ReadWriteLock: concurrent reads, exclusive writes
    static class CachedData {
        private final Map<String, String> cache = new HashMap<>();
        private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
        private final Lock readLock  = rwLock.readLock();
        private final Lock writeLock = rwLock.writeLock();

        public String get(String key) {
            readLock.lock(); // Multiple threads can hold read lock simultaneously
            try {
                return cache.get(key);
            } finally {
                readLock.unlock();
            }
        }

        public void put(String key, String value) {
            writeLock.lock(); // Exclusive — blocks all readers and writers
            try {
                cache.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }

        // StampedLock — Java 8+, optimistic reads (faster for read-heavy)
        private final StampedLock stampedLock = new StampedLock();
        private double x, y;

        public double distanceFromOrigin() {
            long stamp = stampedLock.tryOptimisticRead(); // No lock acquired!
            double currentX = x, currentY = y;
            if (!stampedLock.validate(stamp)) { // Check if write happened during read
                stamp = stampedLock.readLock(); // Fall back to real read lock
                try {
                    currentX = x; currentY = y;
                } finally {
                    stampedLock.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConnectionPool pool = new ConnectionPool(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    String conn = pool.acquire();
                    System.out.println(Thread.currentThread().getName() + " got " + conn);
                    Thread.sleep(200);
                    pool.release(conn);
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }).start();
        }
    }
}