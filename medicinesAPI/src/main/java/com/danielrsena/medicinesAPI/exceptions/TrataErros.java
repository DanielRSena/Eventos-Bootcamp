package com.danielrsena.medicinesAPI.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice //é buscda sempre que acontecer exceções
public class TrataErros {
    

    @ExceptionHandler(EntityNotFoundException.class) //então, o spring sabe que esse método é para a exceção de entidade não encontrada
    public ResponseEntity<?> erro404(){

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> erro400(MethodArgumentNotValidException exception){

        var erros = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(dadosErros::new).toList());
    }

    public record dadosErros(String message, String field){

        public dadosErros(FieldError fieldError){

            this((fieldError.getField()), fieldError.getDefaultMessage());
        }
    }
}
