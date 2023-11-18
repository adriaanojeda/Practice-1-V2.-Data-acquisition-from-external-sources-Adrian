package org.example.model;

import java.time.Instant;

public class Weather {
    Instant ts; // When
    int clouds;
    float rain;
    float wind; // What
    float temp;
    int humidity;
    private Location location;

    public Weather(Instant instant, float temp, float rain, int humidity, int clouds, float wind, Location location) {
        this.ts = instant;
        this.humidity = humidity;
        this.wind = wind;
        this.temp = temp;
        this.clouds = clouds;
        this.rain = rain;
        this.location = location;
    }

    public Weather() {
    }

    public Instant getTs() {
        return ts;
    }

    public int getClouds() {
        return clouds;
    }

    public float getRain() {
        return rain;
    }

    public float getWind() {
        return wind;
    }

    public float getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public Location getLocation() {
        return location;
    }

    public float getPrecipitation() {
        return 0;
    }
}
