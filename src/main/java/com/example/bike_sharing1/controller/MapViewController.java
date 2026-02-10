package com.example.bike_sharing1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapViewController {

    @GetMapping("/map")
    public String showMapPage() {
        return "map"; // Thymeleaf будет искать map.html в templates
    }
}