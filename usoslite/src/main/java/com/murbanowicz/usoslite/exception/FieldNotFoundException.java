package com.murbanowicz.usoslite.exception;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(Long FieldId) {
        super(String.format("Could not find field by id %d", FieldId));
    }
}
