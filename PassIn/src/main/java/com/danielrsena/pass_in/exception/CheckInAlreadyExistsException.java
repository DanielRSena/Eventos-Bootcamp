package com.danielrsena.pass_in.exception;

public class CheckInAlreadyExistsException extends RuntimeException {

    public CheckInAlreadyExistsException(String message){
        super(message);
    }
}