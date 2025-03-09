package com.example.weatherapplication.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(WeatherImageService.class);
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;

    private final Map<String, String> weatherImages = new HashMap<>();

    public WeatherImageService(GridFsTemplate gridFsTemplate, GridFsOperations gridFsOperations) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
        weatherImages.put("ясно", "images/sunny.png");
        weatherImages.put("переменная облачность", "images/partly-cloudy.png");
        weatherImages.put("облачно", "images/cloudy.png");
        weatherImages.put("дождь", "images/rain.png");
        weatherImages.put("гроза", "images/thunderstorm.png");
        weatherImages.put("снег", "images/snow.png");
        weatherImages.put("туман", "images/foggy.png");
    }

    @PostConstruct
    public void initialize() throws IOException {
        try {
            init();
        } catch (IOException e) {
            logger.error("Ошибка при инициализации изображений: {}", e.getMessage());
        }
    }

    public void init() throws IOException {
        for (Map.Entry<String, String> entry : weatherImages.entrySet()) {
            String description = entry.getKey();
            String filePath = entry.getValue();

            GridFSFile existingFile = gridFsTemplate.findOne(
                    new Query(Criteria.where("metadata.description").is(description)));
            if (existingFile == null) {
                try {
                    Resource resource = new ClassPathResource(filePath);
                    if (!resource.exists()) {
                        logger.warn("Файл не найден: {}", filePath);
                        continue;
                    }

                    try (InputStream inputStream = resource.getInputStream()) {
                        gridFsTemplate.store(
                                inputStream,
                                description,
                                "image/png",
                                Map.of("description", description));
                    }
                } catch (IOException e) {
                    logger.error("Ошибка при загрузке файла {}: {}", filePath, e.getMessage());
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
