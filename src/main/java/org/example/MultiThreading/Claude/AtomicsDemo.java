package org.example.MultiThreading.Claude;

import java.util.concurrent.atomic.*;

public class AtomicsDemo {

    // CAS explained: atomically do: if(current == expected) { current = update; return true; }
    static AtomicInteger counter = new AtomicInteger(0);

    // AtomicReference — for lock-free linked list, stack, etc.
    static class LockFreeStack<T> {
        private final AtomicReference<Node<T>> top = new AtomicReference<>(null);

        static class Node<T> {
            final T value;
            final Node<T> next;
            Node(T value, Node<T> next) { this.value = value; this.next = next; }
        }

        public void push(T value) {
            Node<T> newNode;
            Node<T> currentTop;
            do {
                currentTop = top.get();
                newNode = new Node<>(value, currentTop);
            } while (!top.compareAndSet(currentTop, newNode)); // Retry if top changed
        }

        public T pop() {
            Node<T> currentTop;
            Node<T> newTop;
            do {
                currentTop = top.get();
                if (currentTop == null) return null;
                newTop = currentTop.next;
            } while (!top.compareAndSet(currentTop, newTop));
            return currentTop.value;
        }
    }

    // Java 8+ adders — better throughput than AtomicLong under high contention
    // LongAdder stripes the counter across multiple cells
    static LongAdder adder = new LongAdder();
    static LongAccumulator maxValue = new LongAccumulator(Long::max, Long.MIN_VALUE);

    public static void main(String[] args) {
        // Basic atomic operations
        System.out.println(counter.incrementAndGet());  // 1
        System.out.println(counter.addAndGet(5));       // 6
        System.out.println(counter.compareAndSet(6, 10)); // true
        System.out.println(counter.get());              // 10

        // updateAndGet with lambda (Java 8+)
        counter.updateAndGet(v -> v % 2 == 0 ? v / 2 : v * 3 + 1);

        // AtomicReference
        AtomicReference<String> ref = new AtomicReference<>("initial");
        ref.compareAndSet("initial", "updated");
        System.out.println(ref.get()); // updated

        // LockFreeStack
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        stack.push(1); stack.push(2); stack.push(3);
        System.out.println(stack.pop()); // 3

        // LongAdder vs AtomicLong under contention
        adder.increment();
        adder.add(10);
        System.out.println(adder.sum()); // 11

        maxValue.accumulate(42);
        maxValue.accumulate(7);
        maxValue.accumulate(100);
        System.out.println(maxValue.get()); // 100
    }
}