package com.murbanowicz.usoslite.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {
    @Test
    public void shouldAssignStudentRoleUponCreationOfStudentObject(){
        Student student = new Student("name@emai.com", "password", "firstname", "lastname", "123456");

        assertThat(student.getUserRole()).isEqualTo(UserRole.STUDENT);
    }
}