package org.example.MultiThreading;

import java.util.List;
import java.util.concurrent.Callable;

public class CalcParallel<T> implements Callable<T> {

    List<FinData> fD;

    public CalcParallel(List<FinData> fD) {
        this.fD = fD;
    }


    @Override
    public T call() throws Exception {
        //System.out.println("new Thread started");
        new Existing().calc(fD);
        return null;
    }
}
