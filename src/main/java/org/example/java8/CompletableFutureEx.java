package org.example.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Simple async operation
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate long-running task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result from async task";
        });

// Chain operations
        future.thenApply(result -> result.toUpperCase())
                .thenAccept(result -> System.out.println(result));

// Combining futures
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);

        CompletableFuture<Integer> combined = future1.thenCombine(future2,
                (a, b) -> a + b);
        System.out.println(combined.get()); // 30

// Exception handling
        CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Error!");
            return "Success";
        }).exceptionally(ex -> {
            System.out.println("Handled: " + ex.getMessage());
            return "Default value";
        }).thenAccept(System.out::println);
    }
}
