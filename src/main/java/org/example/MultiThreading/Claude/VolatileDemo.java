package org.example.MultiThreading.Claude;

public class VolatileDemo {

    // Visibility guarantee: without volatile, thread may read stale cached value
    private volatile boolean running = true;

    public void stop() {
        running = false; // Write visible to all threads immediately
    }

    public void run() {
        while (running) { // Always reads from main memory
            // do work
        }
        System.out.println("Stopped.");
    }

    // volatile is NOT enough for compound actions
    private volatile int count = 0;

    public void unsafeIncrement() {
        count++; // NOT atomic: read → increment → write (race condition possible)
    }

    // Solution: Use AtomicInteger for atomicity
    private final java.util.concurrent.atomic.AtomicInteger atomicCount
            = new java.util.concurrent.atomic.AtomicInteger(0);

    public void safeIncrement() {
        atomicCount.incrementAndGet(); // Single atomic CAS operation
    }

    // Double-checked locking — requires volatile on instance
    private static volatile VolatileDemo instance;

    public static VolatileDemo getInstance() {
        if (instance == null) {                    // First check (no lock)
            synchronized (VolatileDemo.class) {
                if (instance == null) {            // Second check (with lock)
                    instance = new VolatileDemo(); // volatile prevents partial construction being visible
                }
            }
        }
        return instance;
    }
}