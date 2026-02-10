package com.example.bike_sharing1.repository;

import com.example.bike_sharing1.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findByBikeIdAndEndTimeIsNull(Long bikeId);
    List<Trip> findByUserId(Long userId);
    // Было:
    // Trip findByUserIdAndEndTimeIsNull(Long userId);

    // Стало:
    List<Trip> findByUserIdAndEndTimeIsNull(Long userId);
}
