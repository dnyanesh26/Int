package org.example.MultiT;

public class FinData {

    float PA ;
    float Interest_Rate ;
    float Years ;
    float Compound_Period ;
    float Simple_Int ;
    float Compound_Int ;
    float ROI ;
    FinCalc output;



    public FinCalc getOutput() {
        return output;
    }

    public void setOutput(FinCalc output) {
        this.output = output;
    }

    public float getCompound_Period() {
        return Compound_Period;
    }

    public void setCompound_Period(float compound_Period) {
        Compound_Period = compound_Period;
    }

    public float getPA() {
        return PA;
    }

    public void setPA(float PA) {
        this.PA = PA;
    }

    public float getInterest_Rate() {
        return Interest_Rate;
    }

    public void setInterest_Rate(float interest_Rate) {
        Interest_Rate = interest_Rate;
    }

    public float getYears() {
        return Years;
    }

    public void setYears(float years) {
        Years = years;
    }

    public float getSimple_Int() {
        return Simple_Int;
    }

    public void setSimple_Int(float simple_Int) {
        Simple_Int = simple_Int;
    }

    public float getCompound_Int() {
        return Compound_Int;
    }

    public void setCompound_Int(float compound_Int) {
        Compound_Int = compound_Int;
    }

    public float getROI() {
        return ROI;
    }

    public void setROI(float ROI) {
        this.ROI = ROI;
    }

    @Override
    public String toString() {
        return "FinData{" +
                "PA=" + PA +
                ", Interest_Rate=" + Interest_Rate +
                ", Years=" + Years +
                ", Compound_Period=" + Compound_Period +
                ", Simple_Int=" + Simple_Int +
                ", Compound_Int=" + Compound_Int +
                ", ROI=" + ROI +
                '}';
    }
}
