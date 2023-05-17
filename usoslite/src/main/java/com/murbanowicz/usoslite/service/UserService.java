package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.StudentNotFoundException;
import com.murbanowicz.usoslite.exception.UserNotFoundException;
import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.model.Student;
import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUserById(Long id) {
        User userToDelete = getUserById(id);
        userRepository.delete(userToDelete);
        return userToDelete;
    }

    @Transactional
    public User updateUserById(Long id, User updatedUser) {
        User userToUpdate = getUserById(id);
        userToUpdate.setFirstName(updatedUser.getFirstName());
        userToUpdate.setLastName(updatedUser.getLastName());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPassword(updatedUser.getPassword());
        return userToUpdate;
    }
}
