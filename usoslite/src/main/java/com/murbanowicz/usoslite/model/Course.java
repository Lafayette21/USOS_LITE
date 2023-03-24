package com.murbanowicz.usoslite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
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
    private Field field;

    public Course() {
    }

    public Course(String courseName, int ectsPoints) {
        this.courseName = courseName;
        this.ectsPoints = ectsPoints;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getEctsPoints() {
        return ectsPoints;
    }

    public void setEctsPoints(int ectsPoints) {
        this.ectsPoints = ectsPoints;
    }
}
