package org.example.designPattern.Observer;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay
        implements Observer {
    private float maxTemp = Float.MIN_VALUE, minTemp = Float.MAX_VALUE, sumTemp = 0;
    private int   count = 0;

    public StatisticsDisplay(WeatherDataPush wd) {
        wd.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof WeatherMeasurements) {
            WeatherMeasurements m = (WeatherMeasurements) arg;
            float temp = m.temp;
            sumTemp += temp;
            count++;
            maxTemp = Math.max(maxTemp, temp);
            minTemp = Math.min(minTemp, temp);
            display();
        }
    }


    public void display() {
        System.out.println("Avg/Max/Min temp = "
                + (sumTemp/count) + "/" + maxTemp + "/" + minTemp);
    }
}