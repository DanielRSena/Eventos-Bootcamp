package com.danielrsena.pass_in.exception;

public class AttendeeAlreadyRegisteredException extends RuntimeException {
    
    public AttendeeAlreadyRegisteredException(String message) {
        super(message);
    }

}