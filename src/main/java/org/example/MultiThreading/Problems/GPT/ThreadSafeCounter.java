package org.example.MultiThreading.Problems.GPT;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger(0);


        Runnable r = () -> {
            for(int j=0;j<1000;j++){
                i.incrementAndGet();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i.get());
    }
}
