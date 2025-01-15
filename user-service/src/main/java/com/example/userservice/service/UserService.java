package com.example.userservice.service;

import com.example.userservice.dto.UserCreateDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public void saveUser(UserCreateDTO user) {
        if (userRepository.findByEmail(user.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }


        User u = new User();
        u.setEmail(user.email());
        u.setPassword(passwordEncoder.encode(user.password()));
        u.setName(user.name());
        u.setPhone(user.phone());
        u.setRole(user.role());


        userRepository.save(u);
    }

    public List<UserDTO> getUsersDto(){
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> new UserDTO(u.getName(), u.getEmail(), u.getPhone(), u.getRole())).collect(Collectors.toList());
    }

    public UserDTO getUserDto(Integer id){
        User u = userRepository.findById(id).get();
        return new UserDTO(u.getName(), u.getEmail(), u.getPhone(), u.getRole());
    }
}
