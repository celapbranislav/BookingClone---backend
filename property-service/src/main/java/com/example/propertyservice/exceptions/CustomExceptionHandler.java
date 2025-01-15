package com.example.propertyservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorEntity> handleEntityNotFound(EntityNotFoundException ex){
        ErrorEntity errorEntity = new ErrorEntity(LocalDate.now(), ex.getMessage(), "Trenutno nema vise detalja za error");
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsNotHostException.class)
    public ResponseEntity<ErrorEntity> handleUserIsNotHost(UserIsNotHostException ex){
        ErrorEntity error = new ErrorEntity(LocalDate.now(), ex.getMessage(), "Trenutno nema vise detalja za error");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
