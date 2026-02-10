package com.example.bike_sharing1.controller;


import com.example.bike_sharing1.model.*;
import com.example.bike_sharing1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/bikes")
public class BikeController {
    private final BikeRepository bikeRepository;

    public BikeController(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/seed")
    public void seedBikes() {
        Station station1 = stationRepository.findById(1L).orElse(null);
        Station station2 = stationRepository.findById(2L).orElse(null);

        if (station1 != null && station2 != null) {
            bikeRepository.save(new Bike(55.7510, 37.6175, station1));
            bikeRepository.save(new Bike(55.7558, 37.6173, station2));
        } else {
            System.out.println("Станции не найдены, сначала заполни таблицу станций.");
        }
    }
}