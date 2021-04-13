package com.sample.testservice.controller;

import com.sample.testservice.models.User;
import com.sample.testservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<User> getUser() {
        User user = new User("Abhishek", "Shetty", "abshetty@gmail.com");
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

}
