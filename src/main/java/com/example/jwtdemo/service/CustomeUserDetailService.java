package com.example.jwtauth.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
 class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Dummy user details; replace with database lookup.
        return org.springframework.security.core.userdetails.User.builder()
                .username("test")
                .password("password")
                .roles("USER")
                .build();
    }
}

