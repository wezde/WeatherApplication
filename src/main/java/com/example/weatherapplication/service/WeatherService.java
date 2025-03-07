package com.example.weatherapplication.service;

import com.example.weatherapplication.WeatherRequestHistoryRepository;
import com.example.weatherapplication.dto.WeatherResponse;
import com.example.weatherapplication.entity.WeatherRequestHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherRequestHistoryRepository repository;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    public WeatherResponse getWeather(double latitude, double longitude) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric&lang=ru",
                apiUrl, latitude, longitude, apiKey);
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

        if (response != null) {
            WeatherRequestHistory history = new WeatherRequestHistory(
                    latitude,
                    longitude,
                    response.getMain().getTemp(),
                    response.getWeather()[0].getDescription(),
                    response.getName());
            repository.save(history);
        }
        return response;
    }
}
