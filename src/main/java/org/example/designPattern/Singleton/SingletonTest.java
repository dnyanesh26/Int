package org.example.designPattern.Singleton;






import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonTest {

    @Test
    public void testThreadSafeSingleton() throws InterruptedException {
        int threadCount = 100;
        Set<ThreadSafeSingleton> instances;
        try (ExecutorService service = Executors.newFixedThreadPool(threadCount)) {

            // CountDownLatch ensures all threads start simultaneously
            CountDownLatch startLatch = new CountDownLatch(1);

            // Concurrent set to store unique instances obtained by threads
            instances = Collections.newSetFromMap(new ConcurrentHashMap<>());

            for (int i = 0; i < threadCount; i++) {
                service.submit(() -> {
                    try {
                        startLatch.await(); // Wait for the "starter pistol"
                        instances.add(ThreadSafeSingleton.getInstance());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            startLatch.countDown(); // Release all threads at once
            service.shutdown();
            service.awaitTermination(1, TimeUnit.MINUTES);
        }

        // If thread-safe, the set must contain exactly ONE unique instance
        assertEquals(1, instances.size(), "Singleton failed: multiple instances created!");
    }
}
