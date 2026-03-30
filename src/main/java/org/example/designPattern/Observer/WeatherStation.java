package org.example.designPattern.Observer;

public class WeatherStation {
    public static void main(String[] args) {
        // Pull-style demo
        WeatherData wd = new WeatherData();
        new CurrentConditionsDisplay(wd);
        wd.setMeasurements(22.5f, 65f, 1013f);
        // Push-style demo
        WeatherDataPush wdp = new WeatherDataPush();
        new StatisticsDisplay(wdp);
        wdp.setMeasurements(24.0f, 70f, 1012f);
    }
}
