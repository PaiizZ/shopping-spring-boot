package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import javafx.util.Pair;

public class DiscountPriceStrategy extends DiscountStrategy implements CouponStrategy {
    @Override
    public Pair<String,Float> checkCouponThreshold(Order order, Coupon coupon) {
        if(order.getPrice() >= coupon.getThresholdPrice()) return super.checkDiscountType(coupon);
        return null;
    }
}
