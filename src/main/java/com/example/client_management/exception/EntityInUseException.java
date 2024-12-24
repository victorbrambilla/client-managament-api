package com.example.client_management.exception;


public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String message) {
        super(message);
    }
}
