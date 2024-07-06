package com.danielrsena.pass_in.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(NotFoundException exception){
        return ((BodyBuilder) ResponseEntity.notFound()).body(
            new ExceptionDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage(), LocalDateTime.now())
        );
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity handleConflict(ConflictException exception){
        return ((BodyBuilder) ResponseEntity.badRequest()).body(
            new ExceptionDetails(HttpStatus.CONFLICT.value(), exception.getMessage(), LocalDateTime.now())
        );
    }
}