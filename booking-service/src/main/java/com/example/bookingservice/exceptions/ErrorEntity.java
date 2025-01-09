package com.example.bookingservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ErrorEntity {


    private LocalDate date;
    private String message;
    private String details;
}
