package org.example.MultiThreading.Claude;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

public class ThreadLocalDemo {

    // Classic use case: SimpleDateFormat is NOT thread-safe
    // ThreadLocal gives each thread its own instance
    private static final ThreadLocal<SimpleDateFormat> dateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    // Request context pattern (common in web frameworks)
    public static class RequestContext {
        private static final ThreadLocal<String> userId   = new ThreadLocal<>();
        private static final ThreadLocal<String> tenantId = new ThreadLocal<>();

        public static void set(String user, String tenant) {
            userId.set(user);
            tenantId.set(tenant);
        }

        public static String getUserId()   { return userId.get(); }
        public static String getTenantId() { return tenantId.get(); }

        // CRITICAL: Always clean up to prevent memory leaks in thread pools!
        public static void clear() {
            userId.remove();
            tenantId.remove();
        }
    }

    // InheritableThreadLocal — child threads inherit parent's value
    private static final InheritableThreadLocal<String> inherited =
            new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        // ThreadLocal demo
        Runnable task = () -> {
            String formatted = dateFormat.get().format(new java.util.Date());
            System.out.println(Thread.currentThread().getName() + ": " + formatted);
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) executor.submit(task);

        // Request context demo
        ExecutorService webServer = Executors.newFixedThreadPool(2);
        webServer.submit(() -> {
            RequestContext.set("user123", "tenant-A");
            try {
                handleRequest();
            } finally {
                RequestContext.clear(); // Essential cleanup for thread pool reuse!
            }
        });

        // InheritableThreadLocal demo
        inherited.set("parent-value");
        Thread child = new Thread(() ->
                System.out.println("Child sees: " + inherited.get())); // "parent-value"
        child.start();
        child.join();

        executor.shutdown();
        webServer.shutdown();
    }

    static void handleRequest() {
        // Anywhere in the call stack, get the context without passing it as parameter
        System.out.println("Handling request for: " + RequestContext.getUserId()
                + " tenant: " + RequestContext.getTenantId());
    }
}
