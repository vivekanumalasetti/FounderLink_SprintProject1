package com.founderlink.authservice.service;

import com.founderlink.authservice.dto.*;
import com.founderlink.authservice.entity.*;
import com.founderlink.authservice.repository.UserRepository;
import com.founderlink.authservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final JwtService jwtService;
    private final PasswordEncoder encoder; 

    public AuthResponse register(RegisterRequest req) {

        if (repo.findByEmail(req.getEmail()).isPresent())
            throw new RuntimeException("Email already exists");

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));

       
        user.setRole(Role.valueOf(req.getRole()));

        repo.save(user);

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name(),
                user.getId()
        );

        return new AuthResponse(token, user.getRole().name(), user.getId());
    }

    public AuthResponse login(LoginRequest req) {

        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name(),
                user.getId()
        );

        return new AuthResponse(token, user.getRole().name(), user.getId());
    }

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}