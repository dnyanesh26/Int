package org.example.MultiThreading.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Monitoring {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        });
        // Check if the thread is still running
        boolean isAlive = thread.isAlive();
        // Get the current state (NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED)
        Thread.State state = thread.getState();
        thread.start();
        // Monitoring the state manually
        System.out.println("Thread state: " + thread.getState());
        // Wait for the thread to finish (blocking)
        thread.join();


        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        // Submit a task and monitor via Future
        Future<String> future = executor.submit(() -> {
            Thread.sleep(1000);
            return "Task Complete";
        });
        // Built-in pool statistics
        System.out.println("Active Threads: " + executor.getActiveCount());
        System.out.println("Completed Tasks: " + executor.getCompletedTaskCount());
        System.out.println("Queued Tasks: " + executor.getQueue().size());
        System.out.println("Pool Size: " + executor.getPoolSize());
        // Check individual task status
        if (future.isDone()) {
            System.out.println("Task finished!");
        }
        executor.shutdown();

    }
}
