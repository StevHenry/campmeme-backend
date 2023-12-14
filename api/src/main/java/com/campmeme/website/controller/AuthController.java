package com.campmeme.website.controller;

import com.campmeme.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService service;

    @Autowired
    public AuthController(UserService service){
        this.service = service;
    }


}
