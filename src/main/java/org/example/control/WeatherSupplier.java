package org.example.control;

import org.example.model.Location;
import org.example.model.Weather;

import java.time.Instant;

public interface WeatherSupplier {


        public default Weather getWeather(Location location, Instant ts){

            return null;
        }

}
