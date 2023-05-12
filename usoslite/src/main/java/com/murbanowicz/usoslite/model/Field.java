package com.murbanowicz.usoslite.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "field")
public class Field {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "field")
    private List<Course> courses;

    public Field() {
    }

    public Field(String name) {
        this.name = name;
    }

    public Field(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Field(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field field)) return false;
        return name.equals(field.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
