package com.murbanowicz.usoslite.config;

import com.murbanowicz.usoslite.model.Admin;
import com.murbanowicz.usoslite.model.Student;
import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository) {
        return args -> {
            User student1 = new Student("john@example.com", "password", "John", "Doe", "123456");
            User student2 = new Student("ronald@example.com", "password", "Ronald", "Doe", "654321");
            User admin1 = new Admin("admin1@example.com", "password", "Adam", "Doe");
            User admin2 = new Admin("admin2@example.com", "password", "Thomas", "Doe");

            repository.saveAll(
                    List.of(student1, student2, admin1, admin2)
            );
        };
    }
}
