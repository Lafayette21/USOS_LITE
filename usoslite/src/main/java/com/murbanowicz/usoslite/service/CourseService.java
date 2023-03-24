package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.CourseNotFoundException;
import com.murbanowicz.usoslite.model.Course;
import com.murbanowicz.usoslite.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(()->new CourseNotFoundException(id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course deleteCourseById(Long id) {
        Course courseToDelete = getCourseById(id);
        courseRepository.delete(courseToDelete);
        return courseToDelete;
    }

    public Course updateById(Long id, Course updatedCourse) {
        Course courseToUpdate = getCourseById(id);
        courseToUpdate.setCourseName(updatedCourse.getCourseName());
        courseToUpdate.setEctsPoints(updatedCourse.getEctsPoints());
        return courseToUpdate;
    }
}
