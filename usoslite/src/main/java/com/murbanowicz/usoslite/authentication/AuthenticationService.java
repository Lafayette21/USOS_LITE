package com.murbanowicz.usoslite.authentication;

import com.murbanowicz.usoslite.exception.AuthenticationErrorException;
import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public User authenticate(AuthenticationRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(AuthenticationErrorException::new);
        if (!isCorrectPassword(request, user)){
            throw new AuthenticationErrorException();
        }
        return user;
    }

    private boolean isCorrectPassword(AuthenticationRequest request, User user) {
        return request.getPassword().equals(user.getPassword());
    }
}