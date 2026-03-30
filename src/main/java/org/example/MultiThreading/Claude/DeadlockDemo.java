package org.example.MultiThreading.Claude;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class DeadlockDemo {

    // Classic deadlock scenario
    static Object lockA = new Object();
    static Object lockB = new Object();

    static void deadlockThread1() {
        synchronized (lockA) {
            System.out.println("T1 holds A, waiting for B");
            synchronized (lockB) { System.out.println("T1 holds A and B"); }
        }
    }

    static void deadlockThread2() {
        synchronized (lockB) { // Acquires B first — opposite order to T1!
            System.out.println("T2 holds B, waiting for A");
            synchronized (lockA) { System.out.println("T2 holds B and A"); }
        }
    }

    // PREVENTION 1: Consistent lock ordering — always acquire A before B
    static void safeThread1() {
        synchronized (lockA) { synchronized (lockB) { /* work */ } }
    }
    static void safeThread2() {
        synchronized (lockA) { synchronized (lockB) { /* work */ } } // Same order!
    }

    // PREVENTION 2: tryLock with timeout — break deadlock cycle
    static ReentrantLock rl1 = new ReentrantLock();
    static ReentrantLock rl2 = new ReentrantLock();

    static void safeWithTryLock(ReentrantLock first, ReentrantLock second)
            throws InterruptedException {
        while (true) {
            if (first.tryLock(50, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("Got First Lock: "+ Thread.currentThread().getName());
                    if (second.tryLock(50, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("Got both locks! "+ Thread.currentThread().getName());
                            return; // Got both — do work
                        } finally { second.unlock(); }
                    }
                } finally { first.unlock(); }
            }
            // Didn't get both — back off and retry
            Thread.sleep((long)(Math.random() * 10));
        }
    }

    // PREVENTION 3: Lock ordering by System.identityHashCode
    static void transferMoney(Object from, Object to, double amount) {
        Object first  = System.identityHashCode(from) <= System.identityHashCode(to) ? from : to;
        Object second = first == from ? to : from;
        synchronized (first) {
            synchronized (second) {
                System.out.println("Transferring " + amount);
            }
        }
    }

    // DETECTION: Use ThreadMXBean programmatically
    static void detectDeadlocks() {
        java.lang.management.ThreadMXBean bean =
                java.lang.management.ManagementFactory.getThreadMXBean();
        long[] deadlockedIds = bean.findDeadlockedThreads();
        if (deadlockedIds != null) {
            java.lang.management.ThreadInfo[] infos = bean.getThreadInfo(deadlockedIds);
            for (java.lang.management.ThreadInfo info : infos) {
                System.out.println("Deadlocked: " + info.getThreadName() +
                        " waiting on " + info.getLockName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Start deadlock detector thread
        ScheduledExecutorService detector = Executors.newSingleThreadScheduledExecutor();
        detector.scheduleAtFixedRate(DeadlockDemo::detectDeadlocks, 1, 1, TimeUnit.SECONDS);

        // Demonstrate safe locking
        Thread t1 = new Thread(() -> {
            try { safeWithTryLock(rl1, rl2); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread t2 = new Thread(() -> {
            try { safeWithTryLock(rl2, rl1); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        t1.start(); t2.start();
        t1.join(); t2.join();
        detector.shutdown();
    }
}
