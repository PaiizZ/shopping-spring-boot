package com.example.shopping.services.product;

import com.example.shopping.entities.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
}
