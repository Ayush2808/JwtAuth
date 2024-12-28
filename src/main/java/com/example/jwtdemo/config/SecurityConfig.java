package com.example.jwtdemo.config;

import com.example.jwtdemo.filter.JwtRequestFilter;
import com.example.jwtdemo.service.JwtUtil; // Import JwtUtil here
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF (if you're not using cookies for session management)
                .authorizeRequests(auth -> auth
                        .RequestMatchers("/api/auth/signup", "/api/auth/login").permitAll() // Allow access to signup and login
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter before authentication filter
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoder bean
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // AuthenticationManager bean for managing user authentication
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(userDetailsService, jwtUtil); // JWT request filter bean
    }
}
