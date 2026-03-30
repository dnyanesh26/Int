package org.example.designPattern.Observer;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay
        implements Observer {
    private float temp, hum;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData wd) {
        this.weatherData = wd;
        wd.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            temp = ((WeatherData)o).getTemperature();
            hum  = ((WeatherData)o).getHumidity();
            display();
        }
    }


    public void display() {
        System.out.println("Current: " + temp + "°C, " + hum + "% RH");
    }


}
