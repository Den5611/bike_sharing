package com.example.bike_sharing1.repository;



import com.example.bike_sharing1.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {


}
