package com.sentinel.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private RateLimiterService rateLimiterService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public UserEntity createUser(@Valid @RequestBody UserEntity newUser) {
        return userRepository.save(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int id, @Valid @RequestBody UserEntity updatedUser)  {
        Optional<UserEntity> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserEntity user = existingUser.get();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setApiKey(updatedUser.getApiKey());
        user.setMaxRequests(updatedUser.getMaxRequests());
        user.setWindowSeconds(updatedUser.getWindowSeconds());

        UserEntity saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/request")
    public ResponseEntity<String> simulateRequest(@PathVariable int id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserEntity user = userOpt.get();
        boolean allowed = rateLimiterService.isAllowed(user.getApiKey(), user.getMaxRequests(), user.getWindowSeconds());

        if (allowed) {
            return ResponseEntity.ok("Request allowed for " + user.getUsername());
        } else {
            return ResponseEntity.status(429).body("Rate limit exceeded for " + user.getUsername());
        }
    }
}