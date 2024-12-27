package com.example.jwtdemo.controller;

import com.example.jwtdemo.dto.userdto;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody userdto userdto) {
        // Convert DTO to Entity
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(userdto.getPassword());


        // Call service to sign up user
        return userService.signUp(Userdto);
    }
}

