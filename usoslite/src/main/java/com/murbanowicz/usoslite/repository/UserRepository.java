package com.murbanowicz.usoslite.repository;

import com.murbanowicz.usoslite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
