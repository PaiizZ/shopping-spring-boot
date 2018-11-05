package com.example.shopping.services.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.wrappers.CreateOrderRequest;

public interface CouponService {
    Coupon createCoupon(Coupon coupon);
    Order setBahtDiscount(Order order, CreateOrderRequest createOrderRequest);
}
