package com.murbanowicz.usoslite.controller;

import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUserById(id, updatedUser));
    }

    @PutMapping("/{userId}/set-field/{fieldId}")
    public ResponseEntity<User> updateUsersFieldById(@PathVariable Long userId, @PathVariable Long fieldId){
        return ResponseEntity.ok(userService.updateUserFieldById(userId, fieldId));
    }
}
