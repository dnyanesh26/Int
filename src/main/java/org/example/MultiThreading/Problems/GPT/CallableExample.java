package org.example.MultiThreading.Problems.GPT;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newSingleThreadExecutor();

        Future<Integer> f = ex.submit(()->50);

        ex.shutdown();
        System.out.println(f.get());


    }
}
