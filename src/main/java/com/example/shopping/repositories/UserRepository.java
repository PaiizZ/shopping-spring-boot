package com.example.shopping.repositories;

import com.example.shopping.entities.shopping.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
