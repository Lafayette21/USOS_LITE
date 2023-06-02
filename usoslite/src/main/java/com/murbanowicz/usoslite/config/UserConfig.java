package com.murbanowicz.usoslite.config;

import com.murbanowicz.usoslite.model.*;
import com.murbanowicz.usoslite.repository.CourseRepository;
import com.murbanowicz.usoslite.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository UserRepository, CourseRepository courseRepository) {
        return args -> {
            Field fieldComputerScience = new Field("Computer Science");
            User student1 = new Student("john@example.com", "password", "John", "Doe",
                    "123456", fieldComputerScience);

            Field fieldMathematics = new Field("Mathematics");
            User student2 = new Student("ronald@example.com", "password", "Ronald", "Doe",
                    "654321", fieldMathematics);


            User admin1 = new Admin("admin1@example.com", "password", "Adam", "Doe");
            User admin2 = new Admin("admin2@example.com", "password", "Thomas", "Doe");

            UserRepository.saveAll(
                    List.of(student1, student2, admin1, admin2)
            );

            Course statistics = new Course("Statistics", 5, fieldMathematics);
            Course algebra = new Course("Algebra", 7, fieldMathematics);
            Course oop = new Course("Object Oriented Programming", 5, fieldComputerScience);
            Course database = new Course("Databases 1", 7, fieldComputerScience);

            courseRepository.saveAll(List.of(algebra, statistics, oop, database));
        };
    }
}
