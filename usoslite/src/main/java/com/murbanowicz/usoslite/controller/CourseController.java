package com.murbanowicz.usoslite.controller;

import com.murbanowicz.usoslite.model.Course;
import com.murbanowicz.usoslite.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Course> deleteCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }

    @PostMapping("/update-by-id/{id}")
    public ResponseEntity<Course> updateCourseById(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return ResponseEntity.ok(courseService.updateById(id, updatedCourse));
    }

    @PutMapping("/{courseId}/field/{fieldId}")
    public ResponseEntity<Course> assignCourseToField(@PathVariable Long courseId, @PathVariable Long fieldId) {
        return ResponseEntity.ok(courseService.assignCourseToField(courseId, fieldId));
    }
}
