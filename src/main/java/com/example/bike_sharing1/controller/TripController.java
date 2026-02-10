package com.example.bike_sharing1.controller;

import com.example.bike_sharing1.model.Bike;
import com.example.bike_sharing1.model.Trip;
import com.example.bike_sharing1.model.User;
import com.example.bike_sharing1.repository.BikeRepository;
import com.example.bike_sharing1.repository.TripRepository;
import com.example.bike_sharing1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/rent")
    public ResponseEntity<String> rentBike(@RequestParam Long userId, @RequestParam Long bikeId) {
        User user = userRepository.findById(userId).orElse(null);
        Bike bike = bikeRepository.findById(bikeId).orElse(null);

        if (user == null || bike == null || !bike.isAvailable()) {
            return ResponseEntity.badRequest().body("Ошибка: пользователь или велосипед недоступен");
        }

        bike.setAvailable(false);
        bikeRepository.save(bike);

        Trip trip = new Trip();
        trip.setUser(user);
        trip.setBike(bike);
        trip.setStartTime(LocalDateTime.now());
        tripRepository.save(trip);

        return ResponseEntity.ok("Аренда начата");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBike(@RequestParam Long userId) {
        List<Trip> activeTrips = tripRepository.findByUserIdAndEndTimeIsNull(userId);
        if (activeTrips == null || activeTrips.isEmpty()) {
            return ResponseEntity.badRequest().body("Нет активной аренды");
        }

        // Возьмём первую активную аренду
        Trip activeTrip = activeTrips.get(0);
        activeTrip.setEndTime(LocalDateTime.now());
        tripRepository.save(activeTrip);

        Bike bike = activeTrip.getBike();
        bike.setAvailable(true);
        bikeRepository.save(bike);

        return ResponseEntity.ok("Аренда завершена");
    }


    @GetMapping("/active")
    public ResponseEntity<?> getActiveTrip(@RequestParam Long userId) {
        List<Trip> activeTrips = tripRepository.findByUserIdAndEndTimeIsNull(userId);
        if (activeTrips == null || activeTrips.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Можно вернуть первую активную поездку (если по логике допускается только одна)
        return ResponseEntity.ok(activeTrips.get(0));

        // Или вернуть все:
        // return ResponseEntity.ok(activeTrips);
    }


    @GetMapping("/all")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}
