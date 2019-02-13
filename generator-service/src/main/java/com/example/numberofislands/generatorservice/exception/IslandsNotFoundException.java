package com.example.numberofislands.generatorservice.exception;

public class IslandsNotFoundException extends RuntimeException {
    public IslandsNotFoundException(String message) {
        super(message);
    }
}
