package org.example.MultiThreading.rentrentLock;

import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private final ReentrantLock lock = new ReentrantLock(true);

    public void accessResource() {
        // Acquire the lock
        lock.lock();
        try {
            // Critical section: only one thread can execute this at a time
            System.out.println(Thread.currentThread().getName() + " acquired the fair lock.");
            // Simulate some work

        } finally {
            // Release the lock in a finally block to ensure it always happens
            System.out.println(Thread.currentThread().getName() + " released the fair lock.");
            lock.unlock();

        }
    }

    public static void main(String[] args) {

        LockExample example = new LockExample();
        // here same thread will acquire lock multiple times and release it then next thread will get its turn

        for (int i = 0; i < 5; i++) {
            for(int j=0;j<3;j++) {
                Thread thread = new Thread(() ->
                {


                    example.accessResource();


                }, "Thread-" + i);
                thread.start();
            }
        }
    }
}
