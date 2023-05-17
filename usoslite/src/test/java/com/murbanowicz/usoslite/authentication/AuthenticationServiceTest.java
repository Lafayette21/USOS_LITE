package com.murbanowicz.usoslite.authentication;

import com.murbanowicz.usoslite.exception.AuthenticationErrorException;
import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private UserRepository mockRepository;
    @Mock
    private User mockUser;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(mockRepository);
    }

    @Test
    public void shouldAuthenticateWhenEmailAndPasswordCorrect() {
        when(mockUser.getPassword()).thenReturn("password");
        AuthenticationRequest request = new AuthenticationRequest("student@mail.com", "password");
        when(mockRepository.findUserByEmail(request.getEmail())).thenReturn(Optional.of(mockUser));

        assertThatCode(() -> authenticationService.authenticate(request))
                .doesNotThrowAnyException();
    }

    @Test
    public void shouldThrowExceptionWhenEmailIncorrect() {
        AuthenticationRequest request = new AuthenticationRequest("student@mail.com", "password");
        when(mockRepository.findUserByEmail(request.getEmail())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authenticationService.authenticate(request))
                .isInstanceOf(AuthenticationErrorException.class)
                .hasMessage("Wrong email or password");
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIncorrect() {
        when(mockUser.getPassword()).thenReturn("wrong-password");
        AuthenticationRequest request = new AuthenticationRequest("student@mail.com", "password");
        when(mockRepository.findUserByEmail(request.getEmail())).thenReturn(Optional.of(mockUser));

        assertThatThrownBy(() -> authenticationService.authenticate(request))
                .isInstanceOf(AuthenticationErrorException.class)
                .hasMessage("Wrong email or password");
    }
}