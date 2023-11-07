package org.example.control;

import org.example.model.Location;
import org.example.model.Weather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;


//Implementacion de WheaterSupplier (tiene que tener todos los métodos de WeatherSupplier)
public class OpenWeatherMapSupplier {


    private static final String api = "https://api.openweathermap.org/data/2.5/forecast?lat=%&lon=%&appid=%s&units=metric";
    private static final String apiKey = "apiKey.txt";
    private static final String apiKeyReader = readApiKeyFromFile();

    private static String readApiKeyFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(apiKey))) {
            return br.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Weather getWeather(Location location, Instant timeStamp) {
        //TODO: request Json from API and scrap desired data.
        return null;
    }
}
