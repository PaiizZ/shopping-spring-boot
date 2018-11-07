package com.example.shopping.controllers;

import com.example.shopping.entities.product.User;
import com.example.shopping.services.redis.RedisService;
import com.example.shopping.services.redis.RedisServiceImpl;
import com.example.shopping.services.user.UserService;
import com.example.shopping.services.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RedisService redisService;

    public UserController(UserService userServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder, RedisService redisService) {
        this.userServiceImpl = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.redisService = redisService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.createUser(user));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> unAuthenticateUser(@RequestHeader(value = "Authorization") String token) {
        String[] tokens = token.split(" ");
        redisService.setBlackList(tokens[1]);
        return ResponseEntity.status(HttpStatus.OK).body("Logout Successfully");
    }

    @GetMapping
    public List<User> getAllUser() {
        return userServiceImpl.getAllUser();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }

}
