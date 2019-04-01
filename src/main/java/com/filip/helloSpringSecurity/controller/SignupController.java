package com.filip.helloSpringSecurity.controller;

import com.filip.helloSpringSecurity.entity.User;
import com.filip.helloSpringSecurity.entity.UserRole;
import com.filip.helloSpringSecurity.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class SignupController {

    @Autowired
    private SignupService signupService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user) {
        user.setRoles(Arrays.asList(new UserRole("USER")));
        User newUser = signupService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "Welcome to the about page!";
    }

}
