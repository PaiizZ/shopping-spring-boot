package com.example.shopping.repositories.product;

import com.example.shopping.entities.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long id);
    Long countByUserId(Long id);
}
