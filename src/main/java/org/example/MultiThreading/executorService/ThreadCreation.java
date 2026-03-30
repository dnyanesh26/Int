package org.example.MultiThreading.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCreation {
}

class ManualThreadExample {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            // Manually creating and starting a new thread for EVERY task
            Thread thread = new Thread(() -> {
                System.out.println("Task " + taskId + " running on: " + Thread.currentThread().getName());
            });
            thread.start();
        }
        // No central way to shut them all down; they run until finished.
    }
}

class ExecutorExample {
    public static void main(String[] args) {
        // Create a pool with only 2 reusable worker threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            // Submit tasks to the pool instead of creating new threads
            executor.submit(() -> {
                System.out.println("Task " + taskId + " processed by: " + Thread.currentThread().getName());
            });
        }

        // Properly shut down the service to stop background threads
        executor.shutdown();
    }
}
