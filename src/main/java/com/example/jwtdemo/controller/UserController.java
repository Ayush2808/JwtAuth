package com.example.jwtdemo.controller;


import com.example.jwtdemo.dto.userdto;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.service.JwtUtil;
import com.example.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Signup API
    @PostMapping("/signup")
    public User signUp(@RequestBody userdto userdto) {
        // Convert DTO to Entity
        User user = new User();
        user.setUsername(userdto.getUsername());
        user.setPassword(userdto.getPassword()); // Password should be encoded in the service

        // Call service to sign up user
        return userService.signUp(user);
    }

    // Login API
    @PostMapping("/login")
    public String login(@RequestBody userdto loginDto) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        // If authentication is successful, generate a JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}


