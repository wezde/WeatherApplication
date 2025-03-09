//package com.example.weatherapplication.controller;
//
//import com.example.weatherapplication.dto.WeatherResponse;
//import com.example.weatherapplication.service.WeatherService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/weather")
//@RequiredArgsConstructor
//public class WeatherController {
//
//    private final WeatherService weatherService;
//
//    @GetMapping
//    public WeatherResponse getWeather(@RequestParam double latitude, @RequestParam double longitude) {
//        return weatherService.getWeather(latitude, longitude);
//    }
//}
