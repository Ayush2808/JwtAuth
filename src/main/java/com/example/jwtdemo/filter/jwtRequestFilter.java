package com.example.jwtdemo.filter;

import com.example.jwtdemo.service.JwtUtil;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Get the token from the Authorization header (e.g., "Bearer <token>")
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // Check if the Authorization header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7); // Extract token (remove "Bearer ")
            username = jwtUtil.extractUsername(jwtToken); // Extract username from the token
        }

        // If a valid token and username are found, validate the token
        if (username != null && jwtUtil.validateToken(jwtToken, username)) {
            // Get user details from the UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Set authentication in the SecurityContext
            // You can use an Authentication object to set the context, e.g., with JWT token-based authentication
            // Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {

    }
}
