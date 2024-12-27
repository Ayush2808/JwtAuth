package com.example.jwtdemo.controller;


import com.example.jwtdemo.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public String generateToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }
}

