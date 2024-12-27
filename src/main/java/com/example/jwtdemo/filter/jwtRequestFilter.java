package com.example.jwtdemo.filter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final com.example.jwtdemo.service.JwtUtil jwtUtil;

    public JwtRequestFilter(UserDetailsService userDetailsService, com.example.jwtdemo.service.JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
}
