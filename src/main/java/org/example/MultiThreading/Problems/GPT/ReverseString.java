package org.example.MultiThreading.Problems.GPT;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReverseString {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String str = "abcdefgh";

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        Future<String> f1 = fixedThreadPool.submit(() -> new StringBuilder(str.substring(0,str.length())).reverse().toString());
        Future<String> f2 = fixedThreadPool.submit(() -> new StringBuilder(str.substring(str.length())).reverse().toString());

        System.out.println(f2.get()+f1.get() );
        fixedThreadPool.shutdown();
    }


}
