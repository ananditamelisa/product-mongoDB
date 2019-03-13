package com.example.productmongo.controller;

import com.example.productmongo.api.ApiKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ApiKeyException.class)
    public String apiKey(ApiKeyException exception){
        return "Ups, API key nya kosong"; //WKWKWK
    }
}


