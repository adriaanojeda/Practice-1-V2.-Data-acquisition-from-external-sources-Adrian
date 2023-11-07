package org.example.model;

import java.time.Instant;

public class Weather {   //Event
    private Instant ts; //When

    private String sky;
    private int rain;
    private int wind;     //What
    private int temp;
    private int humedity;

    private Location location; //Where

    public Weather(Instant ts, String sky, int rain, int wind, int temp, int humedity, Location location) {
        this.ts = ts;
        this.sky = sky;
        this.rain = rain;
        this.wind = wind;
        this.temp = temp;
        this.humedity = humedity;
        this.location = location;
    }

    public Instant getTs() {
        return ts;
    }

    public String getSky() {
        return sky;
    }

    public int getRain() {
        return rain;
    }

    public int getWind() {
        return wind;
    }

    public int getTemp() {
        return temp;
    }

    public int getHumedity() {
        return humedity;
    }

    public Location getLocation() {
        return location;
    }

    //Función para pasar la tmp de farenhigt a centigrados

}
