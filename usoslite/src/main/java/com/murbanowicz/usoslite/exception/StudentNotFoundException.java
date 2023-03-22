package com.murbanowicz.usoslite.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long studentId) {
        super(String.format("Could not find student by id %d", studentId));
    }
}
