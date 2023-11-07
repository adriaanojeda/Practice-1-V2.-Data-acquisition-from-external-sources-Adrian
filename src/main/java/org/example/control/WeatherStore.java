package org.example.control;

import org.example.model.Location;
import org.example.model.Weather;

import java.time.Instant;

public interface WeatherStore {
    default void save(Weather weather, Location location, Instant ts){

    }
}
