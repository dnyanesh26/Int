package org.example.MultiThreading.Ques;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Ques1 extends Thread{

    static AtomicInteger i = new AtomicInteger(0);
    static int j = 1;
    static Map<Integer,String> thousand = new HashMap<>(1000);
    public void run(){
        for(int i = 0;i<500;i++) {
            System.out.println(thousand.get(Ques1.i.getAndIncrement()));

        }
    }

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<1000;i++){
            thousand.put(i," "+i+".");
        }
        Thread t1 = new Thread(new Ques1());
        t1.start();



        Thread t2 = new Thread(new Ques1());
        t2.start();
    }
}
