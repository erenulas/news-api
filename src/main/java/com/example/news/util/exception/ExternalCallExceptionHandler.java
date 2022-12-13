package com.example.news.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExternalCallExceptionHandler {

    @ExceptionHandler(ExternalCallErrorException.class)
    public ResponseEntity handleExternalCallErrorException(ExternalCallErrorException ex) {
        return new ResponseEntity<>("Application received an invalid response.", HttpStatus.BAD_GATEWAY);
    }
}
