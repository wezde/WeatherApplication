package com.example.weatherapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {

    private static final String IP_API_URL = "http://ip-api.com/json";
    private final RestTemplate restTemplate;

    public Map<String, Object> getLocation() {
        return restTemplate.getForObject(IP_API_URL, Map.class);
    }
}
