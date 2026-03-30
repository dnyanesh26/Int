package org.example.MultiThreading.Claude;

import java.util.concurrent.locks.*;

public class LockComparison {

    // 1. Synchronized method — locks on 'this'
    private int count1 = 0;
    public synchronized void incrementSync() {
        count1++;
    }

    // 2. Synchronized block — finer-grained control
    private int count2 = 0;
    private final Object lock = new Object();
    public void incrementBlock() {
        // Do non-critical work here...
        synchronized (lock) {
            count2++;
        }
        // Do more non-critical work here...
    }

    // 3. ReentrantLock — most flexible
    private int count3 = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock(true); // fair lock

    public void incrementReentrant() {
        reentrantLock.lock();
        try {
            count3++;
        } finally {
            reentrantLock.unlock(); // Always in finally!
        }
    }

    // 4. tryLock — avoid deadlock scenarios
    public boolean tryIncrement() {
        try {
            if (reentrantLock.tryLock(100, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                try {
                    count3++;
                    return true;
                } finally {
                    reentrantLock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return false; // Didn't get lock
    }

    // 5. Interruptible lock acquisition
    public void interruptibleIncrement() throws InterruptedException {
        reentrantLock.lockInterruptibly(); // throws if thread interrupted while waiting
        try {
            count3++;
        } finally {
            reentrantLock.unlock();
        }
    }
}