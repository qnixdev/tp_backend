package com.trade_platform.Api.Validation;

import com.trade_platform.Controller.Api.ApiController;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(new ErrorResponse(this.getErrors(ex)))
        ;
    }

    private Map<String, String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(e -> this.toSnakeCase(e.getField()), FieldError::getDefaultMessage, (exist, next) -> exist))
        ;
    }

    private String toSnakeCase(String field) {
        return field
            .replaceAll("([a-z])([A-Z])", "$1_$2")
            .toLowerCase()
        ;
    }

    @Getter
    private static class ErrorResponse {
        private final String error = ApiController.API_ERROR_VALIDATION;
        private final Map<String, String> message;

        public ErrorResponse(Map<String, String> message) {
            this.message = message;
        }
    }
}