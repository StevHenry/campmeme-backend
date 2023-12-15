package com.campmeme.website.controller;

import com.campmeme.website.entity.User;
import com.campmeme.website.request.OperationFailed;
import com.campmeme.website.request.UserReducedDataResponse;
import com.campmeme.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id) {
        Optional<User> responseOpt = service.getUserById(id);
        if (responseOpt.isPresent()) {
            User user = responseOpt.get();
            return ResponseEntity.ok(new UserReducedDataResponse(user.getUsername(),
                    user.getId().toHexString()));
        }
        return ResponseEntity.ok(new OperationFailed("Utilisateur inconnu"));
    }
}
