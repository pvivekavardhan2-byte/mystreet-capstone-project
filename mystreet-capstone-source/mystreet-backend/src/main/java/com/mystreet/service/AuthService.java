package com.mystreet.service;

import com.mystreet.dto.AuthDtos.*;
import com.mystreet.entity.User;
import com.mystreet.exception.BadRequestException;
import com.mystreet.repository.UserRepository;
import com.mystreet.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) { this.userRepository = userRepository; this.passwordEncoder = passwordEncoder; this.jwtUtil = jwtUtil; }
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) throw new BadRequestException("Email already registered");
        User u = new User(); u.setEmail(req.email()); u.setPasswordHash(passwordEncoder.encode(req.password())); u.setAdmin(false);
        userRepository.save(u);
        return new AuthResponse(jwtUtil.generateToken(u.getEmail(), u.isAdmin()), u.getId(), u.getEmail(), u.isAdmin());
    }
    public AuthResponse login(LoginRequest req) {
        User u = userRepository.findByEmail(req.email()).orElseThrow(() -> new BadRequestException("Invalid email or password"));
        if (!passwordEncoder.matches(req.password(), u.getPasswordHash())) throw new BadRequestException("Invalid email or password");
        return new AuthResponse(jwtUtil.generateToken(u.getEmail(), u.isAdmin()), u.getId(), u.getEmail(), u.isAdmin());
    }
}
