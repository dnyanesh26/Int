package org.example.MultiThreading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // Shared buffer with a capacity of 5
        SharedBuffer buffer = new SharedBuffer(5);

        // Create producer and consumer threads
        Thread producerThread = new Thread(new Producer(buffer),"producer-1");
        Thread consumerThread = new Thread(new Consumer(buffer),"consumer-1");
        Thread consumerThread2 = new Thread(new Consumer(buffer),"consumer-2");

        // Start the threads

        consumerThread.start();
        producerThread.start();
        consumerThread2.start();
    }
}

// Class representing the shared resource
class SharedBuffer {
    private List<Integer> buffer = new ArrayList<>();
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    // Method to add an item to the buffer (Producer uses this)
    public synchronized void produce(int item) throws InterruptedException {
        // If the buffer is full, wait until there is space
        while (buffer.size() == capacity) {
            System.out.println("Buffer is full. Producer waiting...");
            wait(); // Release the lock and wait
        }

        // Add the item
        buffer.add(item);
        System.out.println(Thread.currentThread().getName()+" Produced: " + item);

        // Notify all waiting threads (specifically the consumer) that the state has changed
        notifyAll();
    }

    // Method to remove an item from the buffer (Consumer uses this)
    public synchronized int consume() throws InterruptedException {
        // If the buffer is empty, wait until an item is produced
        while (buffer.isEmpty()) {
            System.out.println("Buffer is empty. Consumer waiting...");

            wait(); // Release the lock and wait
        }

        // Remove the item
        int item = buffer.remove(0);
        System.out.println(Thread.currentThread().getName()+" Consumed: " + item);

        // Notify all waiting threads (specifically the producer) that the state has changed
        notifyAll();

        return item;
    }
}

// Producer thread implementation
class Producer implements Runnable {
    private SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 21; i++) {
            try {
                buffer.produce(i);
                // Simulate some work time
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Consumer thread implementation
class Consumer implements Runnable {
    private SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            try {
                buffer.consume();
                // Simulate some work time
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
