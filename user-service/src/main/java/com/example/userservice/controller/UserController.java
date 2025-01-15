package com.example.userservice.controller;

import com.example.userservice.authentication.CustomUserDetails;
import com.example.userservice.dto.AuthRequest;
import com.example.userservice.dto.UserCreateDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.models.User;
import com.example.userservice.service.CustomUserDetailsService;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public ResponseEntity<?> welcomePage(@AuthenticationPrincipal CustomUserDetails myUserDetails) {
        String name = myUserDetails.getUsername();
        return ResponseEntity.ok("Welcome " + name);
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody UserCreateDTO user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsersDto(), HttpStatus.OK);
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer idUser) {
        return new ResponseEntity<>(userService.getUserDto(idUser), HttpStatus.OK);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return userService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userService.validateToken(token);
        return "Token is valid";
    }

}
