package org.example.MultiThreading.Claude;

import java.util.concurrent.locks.*;
import java.util.*;

public class WaitNotifyVsCondition {

    // Classic wait/notify bounded buffer
    static class BoundedBuffer<T> {
        private final Queue<T> queue = new LinkedList<>();
        private final int capacity;

        BoundedBuffer(int capacity) { this.capacity = capacity; }

        public synchronized void put(T item) throws InterruptedException {
            while (queue.size() == capacity) {
                wait(); // Release lock and wait — always use while, not if!
            }
            queue.add(item);
            notifyAll(); // Wake all waiters (notifyAll safer than notify)
        }

        public synchronized T take() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            T item = queue.poll();
            notifyAll();
            return item;
        }
    }

    // Same thing with Condition — more efficient (two separate conditions)
    static class BoundedBufferWithCondition<T> {
        private final Queue<T> queue = new LinkedList<>();
        private final int capacity;
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition notFull  = lock.newCondition(); // Only wake producers
        private final Condition notEmpty = lock.newCondition(); // Only wake consumers

        BoundedBufferWithCondition(int capacity) { this.capacity = capacity; }

        public void put(T item) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    notFull.await(); // Only producers wait here
                }
                queue.add(item);
                notEmpty.signal(); // Wake exactly ONE consumer (not all threads)
            } finally { lock.unlock(); }
        }

        public T take() throws InterruptedException {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    notEmpty.await(); // Only consumers wait here
                }
                T item = queue.poll();
                notFull.signal(); // Wake exactly ONE producer
                return item;
            } finally { lock.unlock(); }
        }
    }

    public static void main(String[] args) {
        BoundedBufferWithCondition<Integer> buffer = new BoundedBufferWithCondition<>(5);

        // Producer
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    buffer.put(i);
                    System.out.println("Put: " + i);
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }).start();

        // Consumer
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Took: " + buffer.take());
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }).start();
    }
}
