package com.trade_platform.Controller.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiController {
    public static final String API_INTERNAL_SERVER_ERROR = "API_INTERNAL_SERVER_ERROR";
    public static final String API_ERROR_NOT_FOUND = "API_ERROR_NOT_FOUND";
    public static final String API_ERROR_ALREADY_EXISTS = "API_ERROR_ALREADY_EXISTS";
    public static final String API_ERROR_ACCESS_DENIED = "API_ERROR_ACCESS_DENIED";
    public static final String API_ERROR_VALIDATION = "API_ERROR_VALIDATION";
    public static final String API_ERROR_UNAUTHORIZED = "API_ERROR_UNAUTHORIZED";
    public static final String CUSTOMER_IS_BLOCKED = "CUSTOMER_IS_BLOCKED";

    @SuppressWarnings("unchecked")
    protected <T> ResponseEntity<T> error(String message, String error, HttpStatus status) {
        return ResponseEntity
            .status(status)
            .body((T) new ErrorResponse(error, message))
        ;
    }

    @Getter
    @AllArgsConstructor
    private static class ErrorResponse {
        String error, message;
    }
}