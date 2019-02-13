package com.example.numberofislands.generatorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IslandsExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IslandsErrorResponse> exceptionHandler(IslandsNotFoundException e) {

        IslandsErrorResponse response = new IslandsErrorResponse(
                HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
