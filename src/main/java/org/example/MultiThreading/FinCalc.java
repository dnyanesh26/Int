package org.example.MultiThreading;

public class FinCalc {

    float Simple_Int ;
    float Compound_Int ;
    float ROI ;
    String logs;

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public float getSimple_Int() {
        return Simple_Int;
    }

    public void setSimple_Int(float simple_Int) {
        Simple_Int = simple_Int;
    }

    public float getROI() {
        return ROI;
    }

    public void setROI(float ROI) {
        this.ROI = ROI;
    }

    public float getCompound_Int() {
        return Compound_Int;
    }

    public void setCompound_Int(float compound_Int) {
        Compound_Int = compound_Int;
    }

    @Override
    public String toString() {
        return "FinCalc{" +
                "Simple_Int=" + Simple_Int +
                ", Compound_Int=" + Compound_Int +
                ", ROI=" + ROI +
                '}';
    }
}
