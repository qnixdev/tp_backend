package com.trade_platform.Api.Validation;

import com.trade_platform.Controller.Api.ApiController;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReadableExceptionHandler {
    private static final String SEARCH_TEXT = "JSON parse error:";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handle(HttpMessageNotReadableException ex) {
        String originMessage = ex.getMessage();
        String message = (null != originMessage && originMessage.contains(SEARCH_TEXT))
            ? originMessage.substring(SEARCH_TEXT.length()).trim()
            : originMessage
        ;

        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(new ErrorResponse(message))
        ;
    }

    @Getter
    private static class ErrorResponse {
        private final String error = ApiController.API_ERROR_VALIDATION;
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }
}