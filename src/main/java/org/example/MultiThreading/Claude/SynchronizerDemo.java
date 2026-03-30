package org.example.MultiThreading.Claude;

import java.util.concurrent.*;

public class SynchronizerDemo {

    // 1. CountDownLatch — wait for N events to happen
    static void countDownLatchDemo() throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate   = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    startGate.await(); // All workers wait for start signal
                    System.out.println("Worker " + id + " running");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endGate.countDown();
                }
            }).start();
        }

        System.out.println("Starting all workers...");
        startGate.countDown(); // Release all workers simultaneously
        endGate.await();       // Wait for all to finish
        System.out.println("All workers done");
    }

    // 2. CyclicBarrier — all threads wait at a point, then continue together (reusable)
    static void cyclicBarrierDemo() throws Exception {
        int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties,
                () -> System.out.println("--- All threads reached barrier, proceeding ---"));

        for (int i = 0; i < parties; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + id + " doing phase 1");
                    Thread.sleep((long)(Math.random() * 300));
                    barrier.await(); // Wait for all threads

                    System.out.println("Thread " + id + " doing phase 2");
                    Thread.sleep((long)(Math.random() * 300));
                    barrier.await(); // Reusable! Wait again for phase 2

                } catch (Exception e) { Thread.currentThread().interrupt(); }
            }).start();
        }
    }

    // 3. Phaser — dynamic parties, multi-phase
    static void phaserDemo() {
        Phaser phaser = new Phaser(1); // Register self (main thread)

        for (int i = 0; i < 3; i++) {
            phaser.register(); // Register each worker dynamically
            final int id = i;
            new Thread(() -> {
                System.out.println("Worker " + id + " phase 0");
                phaser.arriveAndAwaitAdvance(); // Arrive and wait for phase 0

                System.out.println("Worker " + id + " phase 1");
                phaser.arriveAndDeregister(); // Done, deregister from phaser
            }).start();
        }

        phaser.arriveAndAwaitAdvance(); // Main thread arrives at phase 0
        System.out.println("All finished phase 0");
        phaser.arriveAndDeregister(); // Main deregisters
    }

    public static void main(String[] args) throws Exception {
        countDownLatchDemo();
        cyclicBarrierDemo();
        Thread.sleep(1000);
        phaserDemo();
    }
}
