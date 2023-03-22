package com.murbanowicz.usoslite.repository;

import com.murbanowicz.usoslite.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
