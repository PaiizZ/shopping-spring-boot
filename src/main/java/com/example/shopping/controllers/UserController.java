package com.example.shopping.controllers;

import com.example.shopping.entity.User;
import com.example.shopping.services.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.createUser(user));
    }

    @GetMapping
    public List<User> getAllUser() {
        return userServiceImpl.getAllUser();
    }

    @GetMapping("{id}")
    public User index(@PathVariable Long id) {
        return userServiceImpl.getUser(id);
    }

}
