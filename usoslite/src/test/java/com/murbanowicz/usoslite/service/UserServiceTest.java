package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.model.UserRole;
import com.murbanowicz.usoslite.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepositoryMock;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepositoryMock);
    }

    @Test
    void shouldGetAllUsers() {
        // when
        userService.getAllUsers();
        // then
        verify(userRepositoryMock).findAll();
    }

    @Test
    void shouldGetUserById() {
        //given
        long userId = 1L;
        User expectedUser = new User("john@example.com", "password", "John", "Doe",
                UserRole.STUDENT);
        //when
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(expectedUser));
        //then
        assertThat(userService.getUserById(userId)).isEqualTo(expectedUser);
    }

    @Test
    void shouldCreateUser() {
        // given
        User expectedUser = new User("john@example.com", "password", "John", "Doe", UserRole.STUDENT);
        // when
        userService.createUser(expectedUser);
        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryMock).save(userArgumentCaptor.capture());

        User actualUser = userArgumentCaptor.getValue();
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void shouldDeleteUserById() {
        //given
        long userId = 1L;
        User expectedUser = new User("john@example.com", "password", "John", "Doe", UserRole.STUDENT);
        //when
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(expectedUser));
        userService.deleteUserById(userId);
        //Then
        verify(userRepositoryMock).delete(expectedUser);
    }

    @Test
    void shouldUpdateUserById() {
        //given
        long userId = 1L;
        User userToUpdate = new User("john@example.com", "password", "John", "Doe", UserRole.STUDENT);
        //when
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(userToUpdate));
        User expectedUpdatedUser = new User("jane@example.com", "password", "Jane", "Smith", UserRole.STUDENT);
        User actualUpdatedUser = userService.updateUserById(userId, expectedUpdatedUser);
        //Then
        assertThat(actualUpdatedUser).isEqualTo(expectedUpdatedUser);
    }
}