package com.example.bike_sharing1.controller;

import com.example.bike_sharing1.model.Station;
import com.example.bike_sharing1.repository.StationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationRepository stationRepository;

    public StationController(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @GetMapping("/seed")
    public void seedStations() {
        stationRepository.save(new Station("Красная Площадь", 55.7539, 37.6208, 100));
        stationRepository.save(new Station("ВДНХ", 55.8298, 37.6331, 100));
        stationRepository.save(new Station("Парк Горького", 55.7296, 37.6031, 100));
    }
}