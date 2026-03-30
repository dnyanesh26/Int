package org.example.MultiThreading.executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2); // Create a thread pool with 2 threads

        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> "Task 1 completed");
        tasks.add(() -> "Task 2 completed");
        tasks.add(() -> {
            Thread.sleep(1000); // Simulate a long-running task
            return "Task 3 completed after 1 second";
        });

        try {
            // invokeAll() blocks until all tasks are done
            List<Future<String>> futures = executorService.invokeAll(tasks);

            for (Future<String> future : futures) {
                // Future.get() will not block here because invokeAll() already waited for completion
                System.out.println(future.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Important to shut down the service to reclaim resources
            executorService.shutdown();
        }
    }
}
