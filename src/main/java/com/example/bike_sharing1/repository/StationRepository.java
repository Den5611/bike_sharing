package com.example.bike_sharing1.repository;


import com.example.bike_sharing1.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}