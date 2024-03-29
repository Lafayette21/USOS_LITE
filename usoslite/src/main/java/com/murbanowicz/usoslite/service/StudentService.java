package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.StudentNotFoundException;
import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.model.Student;
import com.murbanowicz.usoslite.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final FieldService fieldService;

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
        studentToDelete.setField(null);
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

    @Transactional
    public Student updateStudentsFieldById(Long studentId, Long fieldId) {
        Field field = fieldService.getFieldById(fieldId);
        Student studentToUpdate = getStudentById(studentId);
        studentToUpdate.setField(field);
        return studentToUpdate;
    }

    public List<Student> getStudentsByField(Long fieldId) {
        Field field = fieldService.getFieldById(fieldId);
        return getAllStudents().stream()
                .filter(student -> student.getField().equals(field))
                .collect(Collectors.toList());
    }

    public Student createStudentWithExistingField(Student student, Long fieldId) {
        Field existingField = fieldService.getFieldById(fieldId);
        student.setField(existingField);
        studentRepository.save(student);
        return student;
    }
}
