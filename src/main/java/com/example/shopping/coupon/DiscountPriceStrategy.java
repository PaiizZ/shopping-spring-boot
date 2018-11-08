package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import javafx.util.Pair;


public class DiscountPriceStrategy extends DiscountStrategy implements CouponStrategy {
    private static DiscountPriceStrategy instance;

    public static DiscountPriceStrategy getInstance(){
        if(instance == null){
            instance = new DiscountPriceStrategy();
        }
        return instance;
    }

    @Override
    public boolean checkCouponThreshold(Order order, Coupon coupon) {
        if(order.getPrice() >= coupon.getThresholdPrice()) return true;
        return false;
    }

    @Override
    public Pair<String, Float> applyCoupon(Order order, Coupon coupon) {
        if (this.checkCouponThreshold(order, coupon)) return super.getDiscountByType(coupon);
        return null;
    }
}
