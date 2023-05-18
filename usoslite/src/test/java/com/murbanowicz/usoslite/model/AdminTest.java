package com.murbanowicz.usoslite.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdminTest {
    @Test
    public void shouldAssignAdminRoleUponCreationOfAdminObject(){
        Admin admin = new Admin("name@emai.com", "password", "firstname", "lastname");

        assertThat(admin.getUserRole()).isEqualTo(UserRole.ADMIN);
    }
}