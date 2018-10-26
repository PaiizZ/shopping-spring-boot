package com.example.shopping.services.product;

import com.example.shopping.repositories.ProductRepository;

public class ProductServiceImpl {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
