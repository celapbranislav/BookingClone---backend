package com.example.userservice.service;

import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Kreiraj novog korisnika
        User u = new User();
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Enkripcija lozinke
        user.setName(user.getName());
        user.setPhone(user.getPhone());
        user.setRole(user.getRole());

        // Sačuvaj korisnika u bazi
        userRepository.save(user);
    }

}
