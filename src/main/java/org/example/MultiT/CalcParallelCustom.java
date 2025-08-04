package org.example.MultiT;

import java.util.List;
import java.util.concurrent.Callable;

public class CalcParallelCustom implements Runnable {

    List<FinData> fD;

    public CalcParallelCustom(List<FinData> fD) {
        this.fD = fD;
    }


    @Override
    public void run() {
        //System.out.println("new Thread started");
        new Existing().calc(fD);

    }
}
