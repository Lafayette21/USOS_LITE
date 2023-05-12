package com.murbanowicz.usoslite.repository;

import com.murbanowicz.usoslite.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
