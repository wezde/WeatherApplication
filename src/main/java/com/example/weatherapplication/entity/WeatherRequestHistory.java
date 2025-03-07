package com.example.weatherapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "weather_request_history")
@NoArgsConstructor
public class WeatherRequestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double temperature;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime requestTime;

    public WeatherRequestHistory(
            double latitude, double longitude, double temperature, String description, String location) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.description = description;
        this.location = location;
        this.requestTime = LocalDateTime.now();
    }
}
