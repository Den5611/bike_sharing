package com.example.bike_sharing1;

import com.example.bike_sharing1.model.Bike;
import com.example.bike_sharing1.model.Station;
import com.example.bike_sharing1.repository.BikeRepository;
import com.example.bike_sharing1.repository.StationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BikeSharing1Application {

    public static void main(String[] args) {
        SpringApplication.run(BikeSharing1Application.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(StationRepository stationRepository, BikeRepository bikeRepository) {
        return args -> {
            if (stationRepository.count() == 0) {
                stationRepository.save(new Station("Красная Площадь", 55.7539, 37.6208, 100));
                stationRepository.save(new Station("ВДНХ", 55.8298, 37.6331, 100));
                stationRepository.save(new Station("Парк Горького", 55.7296, 37.6031, 100));
            }
            if (bikeRepository.count() == 0) {
                Station station1 = stationRepository.findById(1L).orElse(null);
                Station station2 = stationRepository.findById(2L).orElse(null);

                if (station1 != null && station2 != null) {
                    Bike bike1 = new Bike(55.7539, 37.6150, station1);
                    Bike bike2 = new Bike(55.7558, 37.6173, station2);

                    bikeRepository.save(bike1);
                    bikeRepository.save(bike2);

                    System.out.println("Bike 1 ID: " + bike1.getId());
                    System.out.println("Bike 2 ID: " + bike2.getId());
                }
            }
        };
    }
}
