package com.murbanowicz.usoslite.config;

import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.model.UserRole;
import com.murbanowicz.usoslite.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User user1 = new User("john@example.com", "password", "John", "Doe", UserRole.STUDENT);
            User user2 = new User("admin@example.com", "admin", "Ronald", "Doe", UserRole.ADMIN);

            repository.saveAll(
                    List.of(user1, user2)
            );
        };
    }
}
