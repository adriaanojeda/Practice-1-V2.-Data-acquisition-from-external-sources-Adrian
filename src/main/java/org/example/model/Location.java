package org.example.model;

public class Location extends Weather {
    private int lat;
    private int lon;
    private String island;


    public Location(String lat, float lon, float island) {
        this.lat = lat;
        this.lon = lon;
        this.island = island;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public String getIsland() {
        return island;
    }
}
