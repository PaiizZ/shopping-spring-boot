package com.example.shopping.repositories;

import com.example.shopping.entities.shopping.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
