package org.example.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.model.Location;
import org.example.model.Weather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class WeatherExecuter implements Runnable {

    private OpenWeatherMap openWeatherMapSupplier;
    private SqliteWeather sqliteWeatherStore;

    public WeatherExecuter(OpenWeatherMap openWeatherMapSupplier, SqliteWeather sqliteWeatherStore) {
        this.openWeatherMapSupplier = openWeatherMapSupplier;
        this.sqliteWeatherStore = sqliteWeatherStore;
    }

    private List<Location> readLocationsFromCSV(String csvFilePath) throws IOException {
        List<Location> locations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    double lat = Double.parseDouble(parts[0]);
                    double lon = Double.parseDouble(parts[1]);
                    String name = parts[2].trim();
                    locations.add(new Location(lat, lon, name));
                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir valores en el archivo CSV: " + e.getMessage());

                }
            }
        }
        return locations;
    }


    public void processWeatherData(String csvFile) {
        try {
            List<Location> listLocation = readLocationsFromCSV(csvFile);
            for (Location location : listLocation) {

                List<Weather> weatherList = openWeatherMapSupplier.getWeather(location);
                try {
                    for (Weather weather : weatherList) {
                        String apiUrl = OpenWeatherMap.getApi(location);
                        JsonObject jsonResult = OpenWeatherMap.getJsonData(apiUrl);
                        JsonArray list = jsonResult.getAsJsonArray("list");

                        for (int i = 0; i < list.size(); i++) {
                            JsonObject listItem = list.get(i).getAsJsonObject();
                            long timestamp = listItem.get("dt").getAsLong();
                            Instant instant = Instant.ofEpochSecond(timestamp);

                            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

                            if (localDateTime.getHour() == 12) {
                                sqliteWeatherStore.save(weather, location, instant);
                            }
                        }
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar datos meteorológicos: " + e.getMessage(), e);
        }
    }

    public void run() {
        processWeatherData("C:\\Users\\adrio\\OneDrive\\Escritorio\\Practica 1\\src\\main\\resources\\locations.csv");
    }


}
