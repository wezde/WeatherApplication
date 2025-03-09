package com.example.weatherapplication.view;

import com.example.weatherapplication.dto.WeatherResponse;
import com.example.weatherapplication.service.LocationService;
import com.example.weatherapplication.service.WeatherImageService;
import com.example.weatherapplication.service.WeatherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Logger;

@Route("")
@SpringComponent
@UIScope
public class MainView extends VerticalLayout {

    private static final Logger logger = Logger.getLogger(MainView.class.getName());

    private final WeatherService weatherService;
    private final LocationService locationService;
    private final WeatherImageService weatherImageService;

    private final TextField latitudeField = new TextField("Широта");
    private final TextField longitudeField = new TextField("Долгота");
    private final Button getWeatherButton = new Button("Показать погоду!");
    private final TextArea weatherInfoArea = new TextArea("Информация о погоде");
    private final Image weatherImage = new Image();

    public MainView(WeatherService weatherService,
                    LocationService locationService,
                    WeatherImageService weatherImageService) {
        this.weatherService = weatherService;
        this.locationService = locationService;
        this.weatherImageService = weatherImageService;

        configureLayout();

        loadWeatherForCurrentLocation();

        getWeatherButton.addClickListener(event -> {
            try {
                double latitude = Double.parseDouble(latitudeField.getValue());
                double longitude = Double.parseDouble(longitudeField.getValue());

                WeatherResponse weatherResponse = weatherService.getWeather(latitude, longitude);

                String weatherInfo = weatherResponse.toString();
                weatherInfoArea.setValue(weatherInfo);

                String weatherDescription = weatherResponse.getWeather()[0].getDescription();
                setWeatherImage(weatherDescription);
            } catch (NumberFormatException e) {
                weatherInfoArea.setValue("Пожалуйста, введите корректные координаты.");
            }
        });
    }

    private void configureLayout() {
        HorizontalLayout inputLayout = new HorizontalLayout(latitudeField, longitudeField);

        add(inputLayout, getWeatherButton, weatherInfoArea, weatherImage);

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        weatherInfoArea.setWidth("50%");
        weatherInfoArea.setReadOnly(true);

        weatherImage.setWidth("300px");
        weatherImage.setHeight("300px");
    }

    private void loadWeatherForCurrentLocation() {
        try {
            Map<String, Object> location = locationService.getLocation();
            logger.info("Location response: " + location);
            double latitude = (Double) location.get("lat");
            double longitude = (Double) location.get("lon");

            latitudeField.setValue(String.valueOf(latitude));
            longitudeField.setValue(String.valueOf(longitude));

            WeatherResponse weatherResponse = weatherService.getWeather(latitude, longitude);
            String weatherInfo = weatherResponse.toString();
            weatherInfoArea.setValue(weatherInfo);

            String weatherDescription = weatherResponse.getWeather()[0].getDescription();
            setWeatherImage(weatherDescription);
        } catch (Exception e) {
            logger.info("ОШИБКА: " + e.getMessage());
            weatherInfoArea.setValue("Не удалось определить ваше местоположение.");
        }
    }

    private void setWeatherImage(String weatherDescription) {
        try {
            InputStream imageStream = weatherImageService.getImage(weatherDescription);
            if (imageStream != null) {
                StreamResource resource = new StreamResource("weather-image", () -> imageStream);
                weatherImage.setSrc(resource);
            } else {
                weatherImage.setSrc("images/some_where_wrong.png");
            }
        } catch (IOException e) {
            logger.info("ОШИБКА: " + e.getMessage());
            weatherImage.setSrc("images/some_where_wrong.png");
        }
    }
}


