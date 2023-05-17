package com.murbanowicz.usoslite.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super(String.format("Could not find user by id %d", userId));
    }
}
