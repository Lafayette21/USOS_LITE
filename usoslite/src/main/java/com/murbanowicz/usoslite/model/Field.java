package com.murbanowicz.usoslite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "field")
@Data
@NoArgsConstructor
public class Field {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "field")
    private List<Course> courses;

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
