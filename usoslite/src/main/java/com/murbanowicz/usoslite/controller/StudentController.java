package com.murbanowicz.usoslite.controller;

import com.murbanowicz.usoslite.model.Student;
import com.murbanowicz.usoslite.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/student")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/get-students-by-field/{fieldId}")
    public ResponseEntity<List<Student>> getStudentsByField(@PathVariable Long fieldId){
        return ResponseEntity.ok(studentService.getStudentsByField(fieldId));
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @PostMapping("/create/with-field-id/{fieldId}")
    public ResponseEntity<Student> createStudentWithExistingField(@RequestBody Student student, @PathVariable Long fieldId){
        return ResponseEntity.ok(studentService.createStudentWithExistingField(student, fieldId));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }

    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(studentService.updateStudentById(id, updatedStudent));
    }

    @PutMapping("/{studentId}/set-field/{fieldId}")
    public ResponseEntity<Student> updateStudentsFieldById(@PathVariable Long studentId, @PathVariable Long fieldId) {
        return ResponseEntity.ok(studentService.updateStudentsFieldById(studentId, fieldId));
    }
}
