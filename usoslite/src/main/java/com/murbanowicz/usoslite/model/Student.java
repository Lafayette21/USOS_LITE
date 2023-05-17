package com.murbanowicz.usoslite.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor
public class Student extends User {
    private String studentNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_of_study_id", referencedColumnName = "id")
    private Field field;

    public Student(String email, String password, String firstName, String lastName, UserRole userRole, String studentNumber) {
        super(email, password, firstName, lastName, userRole);
        this.studentNumber = studentNumber;
    }

    public Student(Long id, String email, String password, String firstName, String lastName, UserRole userRole, String studentNumber) {
        super(id, email, password, firstName, lastName, userRole);
        this.studentNumber = studentNumber;
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
