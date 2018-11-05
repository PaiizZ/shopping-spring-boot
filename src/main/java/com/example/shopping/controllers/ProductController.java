package com.example.shopping.controllers;

import com.example.shopping.entities.product.Product;
import com.example.shopping.services.product.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceImpl.createProduct(product));
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable(value = "id") Long id){
        return productServiceImpl.getProductById(id);
    }
}
