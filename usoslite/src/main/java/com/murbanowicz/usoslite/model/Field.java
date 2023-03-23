package com.murbanowicz.usoslite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "field")
public class Field {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Field() {
    }

    public Field(String name) {
        this.name = name;
    }

    public Field(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
