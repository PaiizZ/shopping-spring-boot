package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import javafx.util.Pair;

public interface CouponStrategy {
    Pair<String,Float> checkCouponThreshold(Order order, Coupon coupon);
}
