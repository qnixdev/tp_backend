package com.trade_platform.Service.User.Exception;

public class UserAlreadyExistException extends Exception {
    private static final String MESSAGE_FORMAT = "User with %1$s: %2$s already exist!";

    public UserAlreadyExistException(String field, String value) {
        super(String.format(MESSAGE_FORMAT, field, value));
    }
}