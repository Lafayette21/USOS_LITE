package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.UserNotFoundException;
import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FieldService fieldService;

    public UserService(UserRepository userRepository, FieldService fieldService) {
        this.userRepository = userRepository;
        this.fieldService = fieldService;
    }

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
        userToUpdate.setStudentNumber(updatedUser.getStudentNumber());
        userToUpdate.setFirstName(updatedUser.getFirstName());
        userToUpdate.setLastName(updatedUser.getLastName());
        return userToUpdate;
    }

    @Transactional
    public User updateUserFieldById(Long studentId, Long fieldId) {
        Field field = fieldService.getFieldById(fieldId);
        User userToUpdate = getUserById(studentId);
        userToUpdate.setField(field);
        return userToUpdate;
    }
}
