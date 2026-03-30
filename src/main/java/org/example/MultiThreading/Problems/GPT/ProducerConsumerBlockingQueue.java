package org.example.MultiThreading.Problems.GPT;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueue {



    public static void main(String[] args) {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(5);

        new Thread(() -> {
            for(int i=1;i<=10;i++){
                try {
                    q.put(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Produced "+i);
            }
        }).start();

        new Thread(() -> {
           for(int i=1;i<=10;i++){
               int val = 0;
               try {
                   val = q.take();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               System.out.println("Consumed "+val);
           }
        }).start();
    }
}
