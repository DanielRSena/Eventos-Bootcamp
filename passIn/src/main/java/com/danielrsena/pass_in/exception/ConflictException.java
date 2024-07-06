package com.danielrsena.pass_in.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message){
        super(message);
    }
}