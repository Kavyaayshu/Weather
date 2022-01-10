package com.example.weather;

public class WeatherModel {

    private int temperature;
    private String cityName;
    private String countryName;

    public WeatherModel(int temperature, String cityName, String countryName) {
        this.temperature = temperature;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public int getTemperature() {
        return temperature;
    }


    public String getCityName() {
        return cityName;
    }


    public String getCountryName() {
        return countryName;
    }

}
