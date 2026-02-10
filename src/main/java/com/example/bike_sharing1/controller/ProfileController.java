package com.example.bike_sharing1.controller;

import com.example.bike_sharing1.model.Trip;
import com.example.bike_sharing1.model.User;
import com.example.bike_sharing1.repository.TripRepository;
import com.example.bike_sharing1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile/settings")
    public String profile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            List<Trip> rides = tripRepository.findByUserIdAndEndTimeIsNull(user.getId());
            model.addAttribute("rides", rides);
        }
        model.addAttribute("username", username);
        return "profile";
    }

    @GetMapping("/profile/history")
    public String tripHistory(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            List<Trip> history = tripRepository.findByUserId(user.getId());
            model.addAttribute("history", history);
        }
        return "history";
    }
}
