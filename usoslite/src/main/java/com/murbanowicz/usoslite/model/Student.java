package com.murbanowicz.usoslite.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@DiscriminatorValue("STUDENT")
@NoArgsConstructor
public class Student extends User {
    private String studentNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_of_study_id", referencedColumnName = "id")
    private Field field;

    public Student(String email, String password, String firstName, String lastName, String studentNumber) {
        super(email, password, firstName, lastName);
        this.studentNumber = studentNumber;
        this.userRole = UserRole.STUDENT;
    }

    public Student(Long id, String email, String password, String firstName, String lastName, String studentNumber) {
        super(id, email, password, firstName, lastName);
        this.studentNumber = studentNumber;
        this.userRole = UserRole.STUDENT;
    }

    public Student(String email, String password, String firstName, String lastName, String studentNumber, Field field) {
        super(email, password, firstName, lastName);
        this.studentNumber = studentNumber;
        this.field = field;
        this.userRole = UserRole.STUDENT;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return firstName.equals(student.firstName) &&
                lastName.equals(student.lastName) &&
                studentNumber.equals(student.studentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, studentNumber);
    }
}
