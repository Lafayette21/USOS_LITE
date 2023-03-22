package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.StudentNotFoundException;
import com.murbanowicz.usoslite.model.Student;
import com.murbanowicz.usoslite.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student deleteStudentById(Long id) {
        Student studentToDelete = getStudentById(id);
        studentRepository.delete(studentToDelete);
        return studentToDelete;
    }

    @Transactional
    public Student updateStudentById(Long id, Student updatedStudent) {
        Student studentToUpdate = getStudentById(id);
        studentToUpdate.setStudentNumber(updatedStudent.getStudentNumber());
        studentToUpdate.setFirstName(updatedStudent.getFirstName());
        studentToUpdate.setLastName(updatedStudent.getLastName());
        return studentToUpdate;
    }
}
