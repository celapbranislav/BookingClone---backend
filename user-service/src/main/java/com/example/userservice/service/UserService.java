package com.example.userservice.service;

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


    public void saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }


        User u = new User();
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(user.getName());
        user.setPhone(user.getPhone());
        user.setRole(user.getRole());


        userRepository.save(user);
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
