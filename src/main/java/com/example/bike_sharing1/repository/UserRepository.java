package com.example.bike_sharing1.repository;

import com.example.bike_sharing1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(@Param("username") String username);
}