package org.example.MultiT;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int poolSize) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Thread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Thread(new Worker());
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        if (isShutdown) {
            System.out.println("Cannot submit task, pool is shut down.");
            return;
        }
        try {
            taskQueue.put(task); // Add task to the queue
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        isShutdown = true;
        // Interrupt all worker threads to unblock them from taskQueue.take()
        while(true){
        if(taskQueue.isEmpty()){
        for (Thread worker : workers) {
            worker.interrupt();
        }
            break;
        }}
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            try {
                while (!isShutdown || !taskQueue.isEmpty()) {
                    Runnable task = taskQueue.take(); // Block until a task is available
                    task.run(); // Execute the task
                }
            } catch (InterruptedException e) {
                // Thread interrupted, likely due to shutdown
//                System.out.println(Thread.currentThread().getName() + " interrupted and shutting down.");
            } finally {
//                System.out.println(Thread.currentThread().getName() + " finished.");
            }
        }
    }
}
