package org.example.control;

import org.example.model.Location;

public class WeatherController {
    //Dependencia con Weather y Location

    private Location location;
    private Integer days;
    private WeatherSupplier weatherSupplier;
    private WeatherStore weatherStore;

    public WeatherController(Location location, Integer days, WeatherSupplier weatherSupplier, WeatherStore weatherStore) {
        this.location = location;
        this.days = days;
        this.weatherSupplier = weatherSupplier;
        this.weatherStore = weatherStore;
    }

    public void execute(){
        //TODO
    }
}
