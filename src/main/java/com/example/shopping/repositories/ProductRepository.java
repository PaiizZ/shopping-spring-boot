package com.example.shopping.repositories;

import com.example.shopping.entities.shopping.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
