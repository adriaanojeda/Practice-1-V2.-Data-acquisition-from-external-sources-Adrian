package org.example.control;

import org.example.model.Location;
import org.example.model.Weather;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class OpenWeatherMap implements WeatherStore {

    private static String apiKey;

    public OpenWeatherMap(String apiKey) {
        this.apiKey = apiKey;
    }

    public static String getApi(Location location) {
        return "https://api.openweathermap.org/data/2.5/forecast?lat=" + location.getLat() +
                "&lon=" + location.getLon() + "&appid=" + apiKey + "&units=metric";
    }

    public static JsonObject getJsonData(String api) throws IOException {
        Document result = Jsoup.connect(api).ignoreContentType(true).get();
        JsonParser parser = new JsonParser();
        return parser.parse(result.text()).getAsJsonObject();
    }

    @Override
    public List<Weather> getWeather(Location location) {
        List<Weather> weatherList = new ArrayList<>();
        try {
            String apiUrl = getApi(location);
            JsonObject jsonResult = getJsonData(apiUrl);
            JsonArray list = jsonResult.getAsJsonArray("list");
            for (int i = 0; i < list.size(); i++) {
                JsonObject listItem = list.get(i).getAsJsonObject();
                String hour = String.valueOf(listItem.get("dt_txt")).substring(12, 20);
                String date = listItem.get("dt_txt").getAsString();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
                Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

                if (hour.equals("12:00:00")) {
                    int humidity = listItem.getAsJsonObject("main").get("humidity").getAsInt();
                    double temp = listItem.getAsJsonObject("main").get("temp").getAsDouble();
                    double precipitation = listItem.get("pop").getAsDouble();
                    int clouds = listItem.getAsJsonObject("clouds").get("all").getAsInt();
                    double windSpeed = listItem.getAsJsonObject("wind").get("speed").getAsDouble();
                    Weather weather = new Weather(instant, (float) humidity, (float) windSpeed, (int) temp, clouds, (float) precipitation, location);
                    weatherList.add(weather);
                }
            }
            return weatherList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}