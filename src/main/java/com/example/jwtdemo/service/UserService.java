package com.example.jwtdemo.service;

import com.example.jwtdemo.Repository.UserRepository;


import com.example.jwtdemo.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User signUp(User user) {
        // Check if user already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user to database
        return userRepository.save(user);
    }
}

