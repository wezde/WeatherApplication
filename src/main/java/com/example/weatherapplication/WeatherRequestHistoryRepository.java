package com.example.weatherapplication;

import com.example.weatherapplication.entity.WeatherRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRequestHistoryRepository extends JpaRepository<WeatherRequestHistory, Long> {
}
