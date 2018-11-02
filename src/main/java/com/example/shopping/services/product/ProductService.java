package com.example.shopping.services.product;

import com.example.shopping.entities.shopping.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
}
