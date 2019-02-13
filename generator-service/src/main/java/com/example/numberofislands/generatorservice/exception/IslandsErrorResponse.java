package com.example.numberofislands.generatorservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class IslandsErrorResponse {
    private int status;
    private String message;
    private Long timeStamp;
}
