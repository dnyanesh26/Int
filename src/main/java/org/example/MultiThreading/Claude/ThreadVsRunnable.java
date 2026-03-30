package org.example.MultiThreading.Claude;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Bad: Extending Thread — ties task to thread lifecycle
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

// Good: Implementing Runnable — decoupled, reusable
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task running in: " + Thread.currentThread().getName());
    }
}

// Best: Use with ExecutorService, not raw threads
public class ThreadVsRunnable {
    public static void main(String[] args) {
        // Raw Thread (avoid in production)
        new MyThread().start();

        // Runnable with ExecutorService (preferred)
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new MyTask());
        executor.submit(() -> System.out.println("Lambda Runnable in: " + Thread.currentThread().getName()));
        executor.shutdown();
    }
}