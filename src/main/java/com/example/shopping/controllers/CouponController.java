package com.example.shopping.controllers;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.services.coupon.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {
    @Autowired
    private CouponService couponServiceImpl;

    @PostMapping
    public ResponseEntity<Coupon> createProduct(@RequestBody Coupon coupon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponServiceImpl.createCoupon(coupon));
    }
}
