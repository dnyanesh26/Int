package org.example.MultiThreading;

import java.util.List;

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
