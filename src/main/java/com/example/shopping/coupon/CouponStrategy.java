package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import javafx.util.Pair;

public interface CouponStrategy {
    boolean checkCouponThreshold(Order order, Coupon coupon);
    Pair<String,Float> applyCoupon(Order order, Coupon coupon);
}
