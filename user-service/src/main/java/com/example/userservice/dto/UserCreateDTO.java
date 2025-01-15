package com.example.userservice.dto;

import com.example.userservice.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public record UserCreateDTO(
     String email,
     String password,
     String name,
     Role role,
     String phone
){
}
