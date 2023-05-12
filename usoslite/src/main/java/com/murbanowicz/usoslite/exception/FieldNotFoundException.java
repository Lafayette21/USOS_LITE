package com.murbanowicz.usoslite.exception;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(Long id) {
        super(String.format("Could not find field by id %d", id));
    }
}
