package com.murbanowicz.usoslite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "ects_points")
    private int ectsPoints;

    @ManyToOne
    @JoinColumn(name = "field_id")
    @JsonIgnore
    private Field field;

    public Course(String courseName, int ectsPoints) {
        this.courseName = courseName;
        this.ectsPoints = ectsPoints;
    }

    public Course(String courseName, int ectsPoints, Field field) {
        this.courseName = courseName;
        this.ectsPoints = ectsPoints;
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return ectsPoints == course.ectsPoints && courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, ectsPoints);
    }
}
