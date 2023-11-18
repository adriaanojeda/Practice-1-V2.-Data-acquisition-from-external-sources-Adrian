package org.example.control;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            String apiKey = fileManager.readFile("C:\\Users\\adrio\\OneDrive\\Escritorio\\Practica 1\\src\\main\\resources\\apiKey.txt");

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            long period = 6 * 60 * 60; // el período se especifica en segundos

            String path = "C:\\Users\\adrio\\OneDrive\\Escritorio\\Practica 1\\src\\main\\resources\\path.txt";
            WeatherController weatherController = new WeatherController(new OpenWeatherMapSupplier(apiKey), new SqliteWeatherStore());

            scheduler.scheduleAtFixedRate(() -> {
                try {
                    weatherController.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, period, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
