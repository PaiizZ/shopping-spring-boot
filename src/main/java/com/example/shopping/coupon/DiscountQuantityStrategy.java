package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.entities.product.OrderProduct;
import javafx.util.Pair;


public class DiscountQuantityStrategy extends DiscountStrategy implements CouponStrategy {
    private static DiscountQuantityStrategy instance;


    public static DiscountQuantityStrategy getInstance(){
        if(instance == null){
            instance = new DiscountQuantityStrategy();
        }
        return instance;
    }

    @Override
    public boolean checkCouponThreshold(Order order, Coupon coupon) {
        if(order.getOrderProductList().stream().mapToLong(OrderProduct::getAmount).sum() >= coupon.getThresholdQuantity()) return true;
        return false;
    }

    @Override
    public Pair<String, Float> applyCoupon(Order order, Coupon coupon) {
        if (this.checkCouponThreshold(order, coupon)) return super.getDiscountByType(coupon);
        return null;
    }
}
