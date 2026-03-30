package org.example.MultiThreading.thread;

public class ThreadLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. NEW: Thread object is created but not started
        Thread thread = new Thread(() -> {
            try {
                for(int i=0;i<1000000;i++){

                }
                // 3. TIMED_WAITING: Thread is sleeping for a specific period
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("State after creation: " + thread.getState()); // NEW

        // 2. RUNNABLE: start() is called, thread is ready for CPU
        thread.start();
        System.out.println("State after start(): " + thread.getState()); // RUNNABLE

        System.out.println("State during thread is in loop: " + thread.getState());
        // Give the thread time to enter the sleep state
        Thread.sleep(100);
        System.out.println("State during sleep: " + thread.getState()); // TIMED_WAITING

        // 4. TERMINATED: Wait for the thread to finish its execution
        thread.join();
        System.out.println("State after completion: " + thread.getState()); // TERMINATED
    }
}

