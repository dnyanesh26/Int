package org.example.MultiT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Existing {



    public static void main(String[] args) throws IOException, SQLException {
        List <FinData> finData= new ArrayList<>();
        finData = new DBdata().getFinData();

        long startTimeS = System.currentTimeMillis();
        new Existing().calcSeq(finData);
        long endTimeS   = System.currentTimeMillis();
        System.out.println("S: "+ String.valueOf(endTimeS-startTimeS));

        long startTimeP = System.currentTimeMillis();
        new Existing().calcParallel(finData);
        long endTimeP   = System.currentTimeMillis();
        System.out.println("P: "+ String.valueOf(endTimeP-startTimeP));

        long startTimePS = System.currentTimeMillis();
        new Existing().calcParallelCustom(finData);
        long endTimePS   = System.currentTimeMillis();
        System.out.println("PC: "+ String.valueOf(endTimePS-startTimePS));



    }

    public <T> void calcParallelCustom(List<FinData> finData) throws IOException {

        int nOT = 100;
        List<List<FinData>> listOfSubLists = new ListOfSubLists().createList(finData,nOT);
        List<CalcParallelCustom> lcP= new ArrayList<>();
        for(List<FinData> l: listOfSubLists){
            CalcParallelCustom c = new CalcParallelCustom(l);
            lcP.add(c);
        }
        CustomThreadPool cTP = new CustomThreadPool(listOfSubLists.size());
        for(CalcParallelCustom cPC: lcP){
            cTP.execute(cPC);
        }
        cTP.shutdown();
        new Existing().logging(finData, "logPC");

    }


    public <T> void calcParallel(List<FinData> finData) throws IOException {

        int nOT = 70;
        List<List<FinData>> listOfSubLists = new ListOfSubLists().createList(finData,nOT);
        Collection<CalcParallel<T>> lcP= new ArrayList<>();
        for(List<FinData> l: listOfSubLists){
            CalcParallel c = new CalcParallel(l);
            lcP.add(c);
        }
        try (ExecutorService exP = Executors.newFixedThreadPool(listOfSubLists.size())) {
            exP.invokeAll(lcP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Existing().logging(finData, "logP");

    }

    public void calcSeq(List<FinData> finData) throws IOException {

        new Existing().calc(finData);
        new Existing().logging(finData, "logS");


    }
    public void logging(List<FinData> finData,String filename) throws IOException {
        BufferedWriter logw = new BufferedWriter(new FileWriter("C:\\Proj\\Int\\src\\main\\java\\org\\example\\MultiT\\"+filename+".txt"));
        for(FinData s:finData){
            logw.write(s.getOutput().logs);
        }
        logw.close();
    }


    public void calc(List<FinData> finData) {
        for(FinData f: finData){
            StringBuilder logLine = new StringBuilder();
            FinCalc fc = new FinCalc();
            fc.setROI(new Calculation().getROI(f));
            fc.setCompound_Int(new Calculation().getCI(f));
            fc.setSimple_Int(new Calculation().getSI(f));

            if(f.getCompound_Int()!=fc.getCompound_Int()){
                logLine.append("Compound_Int not matching" + "\n");
                logLine.append(f.toString()).append("\n");
                logLine.append(fc.toString()).append("\n");

            }

            if(f.getSimple_Int()!=fc.getSimple_Int()){
                logLine.append("Simple_Int not matching" + "\n");
                logLine.append(f.toString() + "\n");
                logLine.append(fc.toString() + "\n");

            }


            if(f.getROI()!=fc.getROI()){
                logLine.append("ROI not matching" + "\n");
                logLine.append(f.toString() + "\n");
                logLine.append(fc.toString() + "\n");

            }

            fc.setLogs(String.valueOf(logLine));
            f.setOutput(fc);
        }

    }

}
