package com.trade_platform.Service.Customer.Exception;

public class CustomerAlreadyExistException extends Exception {
    private static final String MESSAGE_FORMAT = "Customer with %1$s: %2$s already exist!";

    public CustomerAlreadyExistException(String field, String value) {
        super(String.format(MESSAGE_FORMAT, field, value));
    }
}