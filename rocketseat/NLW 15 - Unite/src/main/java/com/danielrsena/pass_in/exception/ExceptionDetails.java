package com.danielrsena.pass_in.exception;

import java.time.LocalDateTime;

public record ExceptionDetails (
    Integer status,
    String message, 
    LocalDateTime timestamp
    ){
}