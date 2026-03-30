package org.example.MultiThreading.Claude;

import java.util.concurrent.*;

public class ExecutorServiceDemo {

    public static void main(String[] args) throws Exception {

        // 1. Fixed thread pool — bounded, good for CPU-bound tasks
        ExecutorService fixed = Executors.newFixedThreadPool(4);

        // 2. Cached thread pool — unbounded, reuses idle threads, good for short-lived async tasks
        //    DANGER: Can create thousands of threads under load
        ExecutorService cached = Executors.newCachedThreadPool();

        // 3. Single thread executor — serializes all tasks
        ExecutorService single = Executors.newSingleThreadExecutor();

        // 4. Scheduled thread pool — for delayed/recurring tasks
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        scheduled.scheduleAtFixedRate(() -> System.out.println("Tick"), 0, 1, TimeUnit.SECONDS);

        // 5. PRODUCTION: Build ThreadPoolExecutor directly for full control
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(
                2,                          // corePoolSize
                10,                         // maximumPoolSize
                60L, TimeUnit.SECONDS,      // keepAliveTime for idle threads above core
                new ArrayBlockingQueue<>(100), // bounded work queue (prevents OOM)
                new ThreadFactory() {
                    private int count = 0;
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "custom-worker-" + count++);
                        t.setDaemon(true);
                        return t;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy() // Rejection policy: caller runs task itself
                // Other policies: AbortPolicy (throws), DiscardPolicy, DiscardOldestPolicy
        );

        // Submit tasks — Future for result retrieval
        Future<Integer> future = customPool.submit(() -> {
            Thread.sleep(100);
            return 42;
        });

        System.out.println("Result: " + future.get(500, TimeUnit.MILLISECONDS));

        // Graceful shutdown pattern
        customPool.shutdown();
        if (!customPool.awaitTermination(5, TimeUnit.SECONDS)) {
            customPool.shutdownNow(); // Force if not done
        }

        scheduled.shutdownNow();
        fixed.shutdown();
    }
}
