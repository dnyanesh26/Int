package org.example.MultiThreading.rentrentLock;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample {
    // Create a ReentrantLock with the fairness policy enabled
    private static final ReentrantLock fairLock = new ReentrantLock(true);

    public static void accessResource() {
        // Acquire the lock
        fairLock.lock();
        try {
            // Critical section: only one thread can execute this at a time
            System.out.println(Thread.currentThread().getName() + " acquired the fair lock.");
            // Simulate some work
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Release the lock in a finally block to ensure it always happens
            System.out.println(Thread.currentThread().getName() + " released the fair lock.");
            fairLock.unlock();

        }
    }

    public static void main(String[] args) {


        // Create and start multiple threads
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() ->{ FairLockExample example = new FairLockExample();
                example.accessResource();}
                    , "Thread-" + i);
            thread.start();
        }
    }
}
