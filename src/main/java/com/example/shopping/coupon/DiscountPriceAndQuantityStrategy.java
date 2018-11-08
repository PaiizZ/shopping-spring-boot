package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import javafx.util.Pair;


public class DiscountPriceAndQuantityStrategy extends DiscountStrategy implements CouponStrategy {
    private static DiscountPriceAndQuantityStrategy instance;
    private CouponStrategy priceStrategy;
    private CouponStrategy quantityStrategy;

    public static DiscountPriceAndQuantityStrategy getInstance(){
        if(instance == null){
            instance = new DiscountPriceAndQuantityStrategy();
        }
        return instance;
    }

    public DiscountPriceAndQuantityStrategy() {
        priceStrategy = DiscountPriceStrategy.getInstance();
        quantityStrategy = DiscountQuantityStrategy.getInstance();
    }

    @Override
    public boolean checkCouponThreshold(Order order, Coupon coupon) {
        if (priceStrategy.checkCouponThreshold(order,coupon) && quantityStrategy.checkCouponThreshold(order, coupon))
            return true;
        return false;
    }

    @Override
    public Pair<String, Float> applyCoupon(Order order, Coupon coupon) {
        if (this.checkCouponThreshold(order, coupon)) return super.getDiscountByType(coupon);
        return null;
    }
}
