package org.example.MultiThreading.Claude;

import java.util.concurrent.*;

public class CompletableFutureDemo {

    static ExecutorService pool = Executors.newFixedThreadPool(4);

    // Simulate async operations
    static CompletableFuture<String> fetchUser(int id) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "User-" + id;
        }, pool);
    }

    static CompletableFuture<String> fetchOrders(String user) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "Orders for " + user;
        }, pool);
    }

    public static void main(String[] args) throws Exception {

        // 1. Basic chaining — thenApply (sync transform), thenCompose (async flatMap)
        CompletableFuture<String> pipeline = fetchUser(1)
                .thenCompose(user -> fetchOrders(user))  // Flat-maps async result
                .thenApply(orders -> orders.toUpperCase()) // Transforms result
                .exceptionally(ex -> "Fallback: " + ex.getMessage()); // Error handling

        System.out.println(pipeline.get());

        // 2. Combine two independent futures (parallel execution)
        CompletableFuture<String> userFuture   = fetchUser(1);
        CompletableFuture<String> orderFuture  = fetchUser(2);

        CompletableFuture<String> combined = userFuture.thenCombine(
                orderFuture,
                (u1, u2) -> u1 + " + " + u2
        );
        System.out.println(combined.get());

        // 3. Wait for all / any
        CompletableFuture<Void> allDone = CompletableFuture.allOf(
                fetchUser(1), fetchUser(2), fetchUser(3)
        );
        allDone.get(); // Blocks until all complete
        System.out.println("All users fetched");

        CompletableFuture<Object> anyDone = CompletableFuture.anyOf(
                fetchUser(1), fetchUser(2), fetchUser(3)
        );
        System.out.println("First result: " + anyDone.get());

        // 4. Exception handling strategies
        CompletableFuture<String> withHandle = fetchUser(1)
                .thenApply(u -> { throw new RuntimeException("Simulated failure"); })
                .handle((result, ex) -> {
                    if (ex != null) return "Handled: " + ex.getMessage();
                    return result.toString();
                });
        System.out.println(withHandle.get());

        // 5. Running without return value
        fetchUser(1)
                .thenAccept(user -> System.out.println("Got: " + user)) // consumes result
                .thenRun(() -> System.out.println("Pipeline complete")); // no input/output

        pool.shutdown();
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
