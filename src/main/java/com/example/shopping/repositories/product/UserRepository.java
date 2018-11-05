package com.example.shopping.repositories.product;

import com.example.shopping.entities.product.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
