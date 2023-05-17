package com.murbanowicz.usoslite.authentication;

import com.murbanowicz.usoslite.model.User;
import com.murbanowicz.usoslite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public User authenticate(AuthenticationRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not Found"));
    }
}
