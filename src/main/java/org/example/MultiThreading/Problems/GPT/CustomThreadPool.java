package org.example.MultiThreading.Problems.GPT;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

    private BlockingQueue<Runnable> q = new LinkedBlockingQueue<>();

    CustomThreadPool (int n){
        for(int i=0;i<n;i++){
            new Thread(new Worker()).start();
        }
    }

    void submit(Runnable r){
        q.offer(r);
    }

    class Worker implements Runnable {

        @Override
        public void run() {
            try {
                q.take().run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(10);

        for(int i=1;i<=10;i++){
            int task =i;
            pool.submit(()-> System.out.println("Task submitted: "+task));
        }
    }

}
