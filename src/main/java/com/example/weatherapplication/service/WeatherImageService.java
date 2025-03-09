package com.example.weatherapplication.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherImageService {

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;

    private final Map<String, String> weatherImages = new HashMap<>();

    public WeatherImageService(GridFsTemplate gridFsTemplate, GridFsOperations gridFsOperations) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
        weatherImages.put("ясно", "images/sunny.png");
        weatherImages.put("переменная облачность", "images/partly-cloudy.png");
        weatherImages.put("облачно", "images/cloudy.jpg");
        weatherImages.put("дождь", "images/rain.png");
        weatherImages.put("гроза", "images/thunderstorm.png");
        weatherImages.put("снег", "images/snow.png");
        weatherImages.put("туман", "images/foggy.png");
    }

    @PostConstruct
    public void initialize() throws IOException {
        init();
    }

    public void init() throws IOException {
        for (Map.Entry<String, String> entry : weatherImages.entrySet()) {
            String description = entry.getKey();
            String filePath = entry.getValue();

            GridFSFile existingFile = gridFsTemplate.findOne(
                    new Query(Criteria.where("metadata.description").is(description)));
            if (existingFile == null) {
                Resource resource = new ClassPathResource(filePath);
                try (InputStream inputStream = resource.getInputStream()) {
                    gridFsTemplate.store(
                            inputStream,
                            description,
                            "image/png",
                            Map.of("description", description));
                }
            }
        }
    }

    public InputStream getImage(String weatherDescription) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(
                new Query(Criteria.where("metadata.description").is(weatherDescription)));
        if (file != null) {
            return gridFsOperations.getResource(file).getInputStream();
        }
        return null;
    }
}
