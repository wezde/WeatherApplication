package com.example.weatherapplication.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "weather_images")
public class WeatherImage {

    @Id
    private String id;

    private String weatherDescription;
    private String imageUrl;
}
