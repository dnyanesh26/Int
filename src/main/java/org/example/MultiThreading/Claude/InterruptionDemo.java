package org.example.MultiThreading.Claude;

import java.util.concurrent.*;

public class InterruptionDemo {

    // WRONG: Swallowing InterruptedException
    static void wrong() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // NEVER do this — interrupt flag is cleared and lost!
        }
    }

    // CORRECT option 1: Propagate the exception
    static void correct1() throws InterruptedException {
        Thread.sleep(1000); // Let caller decide how to handle
    }

    // CORRECT option 2: Restore the interrupt flag if you must catch it
    static void correct2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore flag!
            // Clean up and return early
        }
    }

    // Task that responds properly to interruption in a loop
    static class CancellableTask implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Check flag in tight loops (non-blocking work)
                    doWork();

                    // Blocking calls will throw InterruptedException automatically
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // Interrupted during sleep/wait
                System.out.println("Task cancelled via interruption");
                Thread.currentThread().interrupt(); // Restore for callers
            } finally {
                System.out.println("Cleanup resources");
            }
        }

        private void doWork() {
            System.out.println("Working...");
        }
    }

    // Future cancellation — uses interruption under the hood
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(new CancellableTask());

        Thread.sleep(350); // Let task run a bit
        future.cancel(true); // mayInterruptIfRunning = true

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Main done");
    }
}
