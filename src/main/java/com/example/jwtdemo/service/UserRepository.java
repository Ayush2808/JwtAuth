package com.example.jwtdemo.service;

import com.example.jwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}