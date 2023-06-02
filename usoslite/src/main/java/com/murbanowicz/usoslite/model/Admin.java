package com.murbanowicz.usoslite.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ADMIN")
@NoArgsConstructor
public class Admin extends User {
    public Admin(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
        this.userRole = UserRole.ADMIN;
    }

    public Admin(Long id, String email, String password, String firstName, String lastName) {
        super(id, email, password, firstName, lastName);
        this.userRole = UserRole.ADMIN;
    }
}
