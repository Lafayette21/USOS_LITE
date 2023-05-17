package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.UserNotFoundException;
import com.murbanowicz.usoslite.model.Field;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private FieldService fieldServiceMock;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepositoryMock, fieldServiceMock);
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
        long idUser = 1L;
        User expectedUser = new User(idUser, "adam@mail.com" , "pass" ,"Adam", "Abacki",
                "123456", UserRole.STUDENT);
        //when
        when(userRepositoryMock.findById(idUser)).thenReturn(Optional.of(expectedUser));
        //then
        assertThat(userService.getUserById(idUser)).isEqualTo(expectedUser);
    }

    @Test
    void shouldThrowExceptionWhenUserWithIdDoesNotExist() {
        //Then
        assertThatThrownBy(() -> userService.getUserById(1L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Could not find user by id 1");
    }

    @Test
    void shouldAddNewUser() {
        // given
        User expectedUser = new User("adam@mail.com" , "pass" ,"Adam", "Abacki",
                "123456", UserRole.STUDENT);
        ;
        // when
        userService.createUser(expectedUser);
        // then
        ArgumentCaptor<User> UserArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryMock).save(UserArgumentCaptor.capture());

        User actualUser = UserArgumentCaptor.getValue();
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void shouldDeleteUserById() {
        //given
        long idUser = 1L;
        User expectedUser = new User(idUser, "adam@mail.com" , "pass" ,"Adam", "Abacki",
                "123456", UserRole.STUDENT);
        //when
        when(userRepositoryMock.findById(idUser)).thenReturn(Optional.of(expectedUser));
        userService.deleteUserById(idUser);
        //Then
        verify(userRepositoryMock).delete(expectedUser);
    }

    @Test
    void shouldUpdateUserById() {
        //given
        long idUser = 1L;
        User userToUpdate = new User(idUser, "adam@mail.com" , "pass" ,"Adam", "Abacki",
                "123456", UserRole.STUDENT);
        ;
        //when
        when(userRepositoryMock.findById(idUser)).thenReturn(Optional.of(userToUpdate));
        User expectedUpdatedUser = new User("bartosz@mail.com", "pass" ,"Bartosz", "Babacki", "654321", UserRole.STUDENT);
        User actualUpdatedUser = userService.updateUserById(idUser, expectedUpdatedUser);
        //Then
        assertThat(actualUpdatedUser).isEqualTo(expectedUpdatedUser);
    }

    @Test
    public void shouldUpdateUsersField(){
        // given
        Long UserId = 1L;
        Long fieldId = 1L;
        User user = new User("adam@mail.com" , "pass" ,"Adam", "Abacki",
                "123456", UserRole.STUDENT);
        ;
        Field field = new Field("Informatyka");
        // when
        when(userRepositoryMock.findById(UserId)).thenReturn(Optional.of(user));
        when(fieldServiceMock.getFieldById(fieldId)).thenReturn(field);
        //then
        User userFromUpdate = userService.updateUserFieldById(UserId, fieldId);
        assertThat(userFromUpdate.getField()).isEqualTo(field);
    }
}