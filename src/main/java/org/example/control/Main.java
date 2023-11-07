package org.example.control;

import org.example.model.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Location> locations = loadLocationsFromFile("locations.tsv");
        //TODO Leer apiKey


        //TODO Crear controladores


        //TODO Crear la tarea periódica (cada 6h) -> ejecutar tarea
    }

    private static List<Location> loadLocationsFromFile(String filePath) {
        List<Location> locations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split("\t");
                if (parts.length == 3){
                    String name = parts[0];
                    float latitude = Float.parseFloat(parts[1]);
                    float longitude = Float.parseFloat(parts[2]);
                    locations.add(new Location(name, latitude, longitude));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locations;
    }
}