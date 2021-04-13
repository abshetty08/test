package com.sample.testservice.controller;

import com.sample.testservice.exceptions.ResourceNotFoundException;
import com.sample.testservice.models.User;
import com.sample.testservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // POST user
    @PostMapping("/user")
    public User addUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // GET user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));

        return ResponseEntity.ok(user);
    }
    // Update user
    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + user.getId()));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        userRepository.save(existingUser);

        return ResponseEntity.ok().body(existingUser);
    }

    // Delete user
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
