package com.murbanowicz.usoslite.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long courseId) {
        super(String.format("Could not find Course by courseId %d", courseId));
    }
}
