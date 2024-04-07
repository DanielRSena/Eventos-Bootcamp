package com.danielrsena.pass_in.exception;

public class EventFullException extends RuntimeException {
    public EventFullException(String message){
        super(message);
    }
}
