package org.example.MultiThreading.Problems.GPT;

public class OddEven {
    private int num = 1;
    private final int MAX = 10;

    public synchronized void odd() throws InterruptedException {
        while (num <= MAX) {
            while (num % 2 == 0) wait();
            System.out.println("Odd: " + num++);
            notifyAll();
        }
    }

    public synchronized void even() throws InterruptedException {
        while (num <= MAX) {
            while (num % 2 != 0) wait();
            System.out.println("Even: " + num++);
            notifyAll();
        }
    }

    public static void main(String[] args) {
        OddEven obj = new OddEven();
        new Thread(() -> { try { obj.odd(); } catch(Exception e) {} }).start();
        new Thread(() -> { try { obj.even(); } catch(Exception e) {} }).start();
    }
}