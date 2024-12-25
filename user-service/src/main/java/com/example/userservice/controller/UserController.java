package com.example.userservice.controller;

import com.example.userservice.models.User;
import com.example.userservice.service.CustomUserDetailsService;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;


    @GetMapping("/welcome")
    public String welcomePage(){
        return "Welcome";
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

}
