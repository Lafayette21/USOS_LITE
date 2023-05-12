package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.CourseNotFoundException;
import com.murbanowicz.usoslite.model.Course;
import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final FieldService fieldService;

    public CourseService(CourseRepository courseRepository, FieldService fieldService) {
        this.courseRepository = courseRepository;
        this.fieldService = fieldService;
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

    @Transactional
    public Course updateById(Long id, Course updatedCourse) {
        Course courseToUpdate = getCourseById(id);
        courseToUpdate.setCourseName(updatedCourse.getCourseName());
        courseToUpdate.setEctsPoints(updatedCourse.getEctsPoints());
        return courseToUpdate;
    }

    @Transactional
    public Course assignCourseToField(Long courseId, Long fieldId) {
        Course course = getCourseById(courseId);
        Field field = fieldService.getFieldById(fieldId);
        course.setField(field);
        return course;
    }
}
