package org.example.designPattern.Observer;

import java.util.Observable;

public class WeatherDataPush extends Observable {
    private float t, h, p;

    public void setMeasurements(float t, float h, float p) {
        this.t = t; this.h = h; this.p = p;
        measurementsChanged();
    }

    private void measurementsChanged() {
        setChanged();
        notifyObservers(new WeatherMeasurements(t, h, p));  // payload
    }
}

class WeatherMeasurements {
    public final float temp, humidity, pressure;
    public WeatherMeasurements(float t, float h, float p) {
        temp = t; humidity = h; pressure = p;
    }
}