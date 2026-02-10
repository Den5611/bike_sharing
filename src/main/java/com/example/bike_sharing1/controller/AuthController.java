package com.example.bike_sharing1.controller;




import com.example.bike_sharing1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.servlet.http.HttpSession;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;


    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        try {
            userService.loginUser(username, password);
            session.setAttribute("username", username);
            return "success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model,
                           HttpSession session) {
        try {
            System.out.println("Попытка регистрации: " + username); // добавь это
            userService.registerUser(username, password);
            session.setAttribute("username", username);
            return "success";
        } catch (RuntimeException e) {
            e.printStackTrace();  // Печать полного стека ошибки
            model.addAttribute("error", e.getMessage());
            return "index";
        }
    }
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/";
        }

        model.addAttribute("username", username);
        // Здесь можно добавить загрузку истории поездок и другой информации пользователя
        // model.addAttribute("rides", rideService.getRidesByUsername(username));
        // model.addAttribute("activeRide", rideService.getActiveRideByUsername(username));

        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}