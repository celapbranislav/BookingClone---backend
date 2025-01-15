package com.example.userservice.dto;

import com.example.userservice.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String username;
    private String email;
    private String phoneNumber;
    private Role role;




}
