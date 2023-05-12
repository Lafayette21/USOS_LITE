package com.murbanowicz.usoslite.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("Could not find user by id %d", id));
    }
}
