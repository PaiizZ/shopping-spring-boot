package com.example.shopping.repositories.product;

import com.example.shopping.entities.product.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
