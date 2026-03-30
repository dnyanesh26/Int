package org.example.MultiThreading.Problems.GPT;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class PC {
    private Queue<Integer> q = new ArrayDeque<>();
    private int cap =5;

    public synchronized void produce(int val) throws InterruptedException {
        while(q.size()==cap) wait();
        q.offer(val);
        System.out.println("Produced " + val);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while(q.isEmpty()) wait();
        int val = q.poll();
        System.out.println("Consumed " + val);
        notifyAll();
        return val;
    }

    public static void main(String[] args) {
        PC pc = new PC();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++)
                try { pc.produce(i); } catch(Exception ignored) {}

        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++)
                try { pc.consume(); } catch(Exception ignored) {}
        }).start();
    }
}
