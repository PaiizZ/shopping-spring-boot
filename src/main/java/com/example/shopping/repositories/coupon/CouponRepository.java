package com.example.shopping.repositories.coupon;

import com.example.shopping.entities.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}