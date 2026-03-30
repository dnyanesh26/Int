package org.example.MultiThreading.thread;

public class ThreadStatesDemoLockCheck {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // Thread 1: Will hold the lock and then go into WAITING state
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {

                    System.out.println("T1 entering sleep state");
                    //sleep doesn't release lock and other thread is blocked
                    Thread.sleep(5000);
                    System.out.println("T1 reacquiring lock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-1");

        // Thread 2: Will try to enter the synchronized block but find it locked
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                // Critical section
                System.out.println("T2 acquires lock");
            }
        }, "Thread-2");

        // Start T1 and give it time to enter the synchronized block and sleep()
        t1.start();
        Thread.sleep(100);
        System.out.println("T1 state after sleep(): " + t1.getState()); // WAITING

        // Start T2 while T1 is sleeping.
        t2.start();
        System.out.println("T2 state after start: " + t2.getState()); // RUNNABLE
        Thread.sleep(100);
        System.out.println("T2 state after start: " + t2.getState()); // BLOCKED
        t1.join();
//        Thread.sleep(100);
        // To show BLOCKED, we need T2 to hold the lock while T1 is in sleep state

        synchronized (lock) {

            Thread.sleep(100); // Main thread holds the lock, blocking T2 if T1.join is not present
            System.out.println("Main thread has lock now"); // BLOCKED

        }
    }
}

