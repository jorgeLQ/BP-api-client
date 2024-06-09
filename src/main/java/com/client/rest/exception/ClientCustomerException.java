package com.client.rest.exception;

public class ClientCustomerException extends RuntimeException {
    public ClientCustomerException(String message) {
        super(message);
    }
}