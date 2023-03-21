package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.model.Student;
import com.murbanowicz.usoslite.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
