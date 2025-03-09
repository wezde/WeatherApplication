package com.example.weatherapplication.dto;

import lombok.Data;

@Data
public class WeatherResponse {

    private String name;
    private Main main;
    private Weather[] weather;

    @Data
    public static class Main {
        private double temp;
        private double feels_like;
    }

    @Data
    public static class Weather {
        private String description;
        private String icon;
    }

    @Override
    public String toString() {
        return String.format(
                "Город: %s\nТемпература: %.1f°C\nОщущается как: %.1f°C\nПогода: %s",
                name, main.getTemp(), main.getFeels_like(), weather[0].getDescription()
        );
    }
}
