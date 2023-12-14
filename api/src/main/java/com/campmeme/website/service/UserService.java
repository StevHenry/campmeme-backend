package com.campmeme.website.service;

import com.campmeme.website.entity.User;
import com.campmeme.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Hasher le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Enregistrer l'utilisateur dans la base de données
        return userRepository.save(user);

    }
}
