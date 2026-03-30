package org.example.MultiThreading.thread;

public class ThreadStatesDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // Thread 1: Will hold the lock and then go into WAITING state
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    // Voluntarily releases lock and enters WAITING state
                    System.out.println("T1 entering waiting state");
                    lock.wait();
                    System.out.println("T1 reacquiring lock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-1");

        // Thread 2: Will try to enter the synchronized block
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                // Critical section
                System.out.println("T2 acquires lock");
            }
        }, "Thread-2");

        // Start T1 and give it time to enter the synchronized block and wait()
        t1.start();
        Thread.sleep(100);
        System.out.println("T1 state after wait(): " + t1.getState()); // WAITING

        // Start T2 while T1 is waiting. Since T1 released the lock, T2 enters.
        t2.start();
        Thread.sleep(100);

        System.out.println("T1 state after wait(): " + t1.getState()); // WAITING
        synchronized (lock) {
            lock.notify(); // T1 is signaled, but it must re-acquire the lock
            Thread.sleep(100); // Main thread holds the lock, blocking T1
            System.out.println("T1 state after notify() but lock still held: " + t1.getState()); // BLOCKED

        }
    }
}

