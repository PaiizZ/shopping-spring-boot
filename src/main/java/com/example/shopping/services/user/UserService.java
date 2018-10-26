package com.example.shopping.services.user;

import com.example.shopping.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUser();
    User getUser(Long id);
}
