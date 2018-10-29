package com.example.shopping.services.user;

import com.example.shopping.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User createUser(User user);
    List<User> getAllUser();
    User getUserById(Long id);
}
