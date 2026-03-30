package org.example.designPattern.Observer;

import java.util.Observable;

public class WeatherData extends Observable {
    private float temperature, humidity, pressure;

    public void setMeasurements(float t, float h, float p) {
        this.temperature = t; this.humidity = h; this.pressure = p;
        measurementsChanged();
    }

    private void measurementsChanged() {
        setChanged();          // mark internal flag
        notifyObservers();     // no payload → pull
    }

    // Getters for observers:
    public float getTemperature() { return temperature; }
    public float getHumidity()    { return humidity;    }
    public float getPressure()    { return pressure;    }
}