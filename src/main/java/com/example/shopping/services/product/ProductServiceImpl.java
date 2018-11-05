package com.example.shopping.services.product;

import com.example.shopping.entities.product.Product;
import com.example.shopping.exceptions.ProductNotFoundException;
import com.example.shopping.repositories.coupon.CouponRepository;
import com.example.shopping.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private CouponRepository couponRepository;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found."));
    }
}
