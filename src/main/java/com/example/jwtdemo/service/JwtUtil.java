package com.example.jwtdemo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "ghghttghiykuulkgjkihfbmjghghkuuiiujhjfgf";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Generate JWT token for the given username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate the token by checking username and expiration
    public boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // Extract all claims from the token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder() // Updated method
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token has expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}


