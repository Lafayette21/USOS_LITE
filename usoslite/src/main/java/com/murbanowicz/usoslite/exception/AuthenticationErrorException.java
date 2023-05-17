package com.murbanowicz.usoslite.exception;

public class AuthenticationErrorException extends RuntimeException {
    public AuthenticationErrorException() {
        super("Wrong email or password");
    }
}
