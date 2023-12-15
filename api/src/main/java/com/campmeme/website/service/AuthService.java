package com.campmeme.website.service;

import com.campmeme.website.entity.User;
import com.campmeme.website.repository.UserRepository;
import com.campmeme.website.request.UserCreation;
import com.campmeme.website.request.UserLoginRequest;
import com.campmeme.website.request.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User createUser(UserCreation userCreation) {
        String password = passwordEncoder.encode(userCreation.getPassword());

        User user = new User(userCreation.getUsername(), password, 0, userCreation.getEmail());
        userRepository.save(user);
        return user;
    }

    public Optional<UserLoginResponse> attemptLogin(UserLoginRequest credentials) {
        Optional<User> userOpt = userRepository.findByEmail(credentials.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
                return Optional.of(new UserLoginResponse(user.getUsername(),
                        user.getGroupId(), user.getId().toHexString()));
            }
        }
        return Optional.empty();
    }
}
