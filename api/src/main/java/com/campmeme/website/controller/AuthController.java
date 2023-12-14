package com.campmeme.website.controller;

import com.campmeme.website.entity.User;
import com.campmeme.website.request.OperationFailed;
import com.campmeme.website.request.UserCreation;
import com.campmeme.website.request.UserLoginRequest;
import com.campmeme.website.request.UserLoginResponse;
import com.campmeme.website.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserCreation userData) {
        if (service.emailExists(userData.getEmail())) {
            return ResponseEntity.ok(new OperationFailed("Email déjà enregistré"));
        }
        User user = service.createUser(userData);
        return ResponseEntity.ok(new UserLoginResponse(user.getUsername(),
                user.getGroupId(), user.getId().toHexString()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> attemptLogin(@RequestBody UserLoginRequest loginRequest) {
        Optional<UserLoginResponse> responseOpt = service.attemptLogin(loginRequest);
        if (responseOpt.isPresent()) {
            return ResponseEntity.ok(responseOpt.get());
        }
        return ResponseEntity.ok(new OperationFailed("Identification échouée"));
    }
}
