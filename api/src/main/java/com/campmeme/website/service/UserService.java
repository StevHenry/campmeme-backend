package com.campmeme.website.service;

import com.campmeme.website.entity.User;
import com.campmeme.website.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(String userId) {
        try {
            return userRepository.findById(new ObjectId(userId));
        } catch (IllegalArgumentException exception) {
            return Optional.empty();
        }
    }
}
