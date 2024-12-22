package com.example.propertyservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException{
    private String message;
}
