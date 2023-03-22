package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.StudentNotFoundException;
import com.murbanowicz.usoslite.model.Student;
import com.murbanowicz.usoslite.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepositoryMock;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepositoryMock);
    }

    @Test
    void shouldGetAllStudents() {
        // when
        studentService.getAllStudents();
        // then
        verify(studentRepositoryMock).findAll();
    }

    @Test
    public void shouldGetStudentById() {
        //given
        long idStudent = 1L;
        Student expectedStudent = new Student(idStudent, "Adam", "Abacki", "123456");
        //when
        when(studentRepositoryMock.findById(idStudent)).thenReturn(Optional.of(expectedStudent));
        //then
        assertThat(studentService.getStudentById(idStudent)).isEqualTo(expectedStudent);
    }

    @Test
    public void shouldThrowExceptionWhenStudentWithIdDoesNotExist() {
        //Then
        assertThatThrownBy(() -> studentService.getStudentById(1L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Could not find student by id 1");
    }

    @Test
    public void shouldAddNewStudent() {
        // given
        Student expectedStudent = new Student("Adam", "Abacki", "123456");
        // when
        studentService.createStudent(expectedStudent);
        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepositoryMock).save(studentArgumentCaptor.capture());

        Student actualStudent = studentArgumentCaptor.getValue();
        assertThat(actualStudent).isEqualTo(expectedStudent);
    }

    @Test
    public void shouldDeleteStudentById() {
        //given
        long idStudent = 1L;
        Student expectedStudent = new Student(idStudent, "Adam", "Abacki", "123456");
        //when
        when(studentRepositoryMock.findById(idStudent)).thenReturn(Optional.of(expectedStudent));
        studentService.deleteStudentById(idStudent);
        //Then
        verify(studentRepositoryMock).delete(expectedStudent);
    }

    @Test
    public void shouldUpdateStudentById() {
        //given
        long idStudent = 1L;
        Student studentToUpdate = new Student("Adam", "Abacki", "123456");
        //when
        when(studentRepositoryMock.findById(idStudent)).thenReturn(Optional.of(studentToUpdate));
        Student expectedUpdatedStudent = new Student("Bartosz", "Babacki", "654321");
        Student actualUpdatedStudent = studentService.updateStudentById(idStudent, expectedUpdatedStudent);
        //Then
        assertThat(actualUpdatedStudent).isEqualTo(expectedUpdatedStudent);
    }
}