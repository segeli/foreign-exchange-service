package com.foreignexchange.controller;

import com.foreignexchange.exception.InvalidFormatError;
import com.foreignexchange.exception.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<InvalidFormatError> handleInvalidFormat(InvalidFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(InvalidFormatError.builder().reason(ex.getMessage()).build());
    }
}
