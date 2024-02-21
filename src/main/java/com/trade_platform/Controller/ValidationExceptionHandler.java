package com.trade_platform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex
            .getBindingResult()
            .getFieldErrors()
            .forEach(e -> errors.put(this.toSnakeCase(e.getField()), e.getDefaultMessage()))
        ;

        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(errors)
        ;
    }

    private String toSnakeCase(String field) {
        return field.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
}