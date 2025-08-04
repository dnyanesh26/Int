package org.example.MultiT;

public class Calculation {

    public float getROI(FinData f){
        return Float.parseFloat(String.format("%.3f",((getCI(f)/f.getPA()))))*100;
    }

    public float getCI(FinData f){
//        float PA =75310.86F;
//        if(f.getPA()==PA){
//            float B = Float.parseFloat(String.format("%.4f",Math.pow((float)(1+((float)f.getInterest_Rate()/(float) (100*f.getCompound_Period()))),(float)(f.getCompound_Period()*f.getYears()))));
//            System.out.println(f.getPA()*B - f.getPA());
//            System.out.println("B: "+B);
//        }
        return f.getPA()*Float.parseFloat(String.format("%.3f",Math.pow((1+(f.getInterest_Rate()/ (100*f.getCompound_Period()))),(float)(f.getCompound_Period()*f.getYears())))) - f.getPA();
    }

    public float getSI(FinData f){
        return (Float.parseFloat(String.format("%.3f",f.getPA()))*Float.parseFloat(String.format("%.3f",f.getInterest_Rate()))*Float.parseFloat(String.format("%.3f",f.getYears())))/100;
    }
}
