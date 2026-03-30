package org.example.MultiThreading.executorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 1. Define a Callable task that returns a String
        Callable<String> task = () -> {
            Thread.sleep(1000); // Simulate long-running work
            return "Task completed successfully!";
        };
        System.out.println("Submitting task...");
        // 2. Submit the task and get a Future "placeholder"
        Future<String> future = executor.submit(task);
        // 3. Do other work while the task runs in the background
        System.out.println("Main thread is free to do other things...");
        // 4. Retrieve the result (this will block until the 1s sleep is over)
        String result = future.get();
        System.out.println("Result from background: " + result);
        executor.shutdown();

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int val = i;
            futures.add(executor.submit(() -> val * val));
        }
        // Now retrieve results after all have been submitted
        for (Future<Integer> f : futures) {
            System.out.println("Computed: " + f.get());
        }

        ExecutorService executor1 = Executors.newFixedThreadPool(3);
        // 1. Prepare a collection of Callable tasks
        List<Callable<Integer>> tasks = Arrays.asList(
                () -> { Thread.sleep(1000); return 10; },
                () -> { Thread.sleep(500);  return 20; },
                () -> { Thread.sleep(2000); return 30; }
        );
        // 2. invokeAll blocks here until ALL tasks are done
        List<Future<Integer>> futures1 = executor1.invokeAll(tasks);
        // 3. Iterate to get results (these calls to .get() will NOT block further)
        for (Future<Integer> future1 : futures1) {
            System.out.println("Result: " + future1.get());
        }
        executor.shutdown();
    }
}
