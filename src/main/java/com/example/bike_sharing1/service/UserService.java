package com.example.bike_sharing1.service;

import com.example.bike_sharing1.model.User;
import com.example.bike_sharing1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String password) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            System.out.println("Пользователь уже существует: " + username);
            throw new RuntimeException("Такой пользователь уже есть!");
        }

        User user = new User(username, password);
        User saved = userRepository.save(user);
        System.out.println("Пользователь сохранён: " + saved.getId() + ", " + saved.getUsername());
        return saved;
    }


    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Неправильное имя или пароль!");
        }
        return user;
    }
}