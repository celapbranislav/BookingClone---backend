package com.example.bookingservice.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DateIsInvalidException extends RuntimeException{
    private String message;
}
