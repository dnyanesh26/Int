package org.example.Collections.Claude;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueDemo {
    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    static AtomicInteger produced = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        // Producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    queue.put(i);   // blocks if full
                    produced.incrementAndGet();
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        // Consumer
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    int val = queue.take(); // blocks if empty
                    System.out.println("Consumed: " + val);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });

        producer.start(); consumer.start();
        producer.join(); consumer.join();

        // Priority-based processing
        PriorityBlockingQueue<Task> pq = new PriorityBlockingQueue<>();
        pq.put(new Task(3, "low"));
        pq.put(new Task(1, "HIGH"));
        pq.put(new Task(2, "medium"));
        System.out.println(pq.take().name); // HIGH (priority 1)

        // DelayQueue - scheduled task execution
        DelayQueue<DelayedTask> dq = new DelayQueue<>();
        dq.put(new DelayedTask("run-after-2s", 2000));
        DelayedTask task = dq.take(); // blocks until delay expires
        System.out.println("Executed: " + task.name);
    }
}

class Task implements Comparable<Task> {
    int priority; String name;
    Task(int p, String n) { priority = p; name = n; }
    public int compareTo(Task o) { return Integer.compare(priority, o.priority); }
}

class DelayedTask implements Delayed {
    String name; long triggerTime;
    DelayedTask(String n, long delayMs) {
        name = n; triggerTime = System.currentTimeMillis() + delayMs;
    }
    public long getDelay(TimeUnit u) {
        return u.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
    public int compareTo(Delayed o) {
        return Long.compare(triggerTime, ((DelayedTask)o).triggerTime);
    }
}

