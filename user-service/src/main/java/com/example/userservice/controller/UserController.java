package com.example.userservice.controller;

import com.example.userservice.models.User;
import com.example.userservice.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    @GetMapping("/welcome")
    public String welcomePage(){
        return "Welcome";
    }


}
