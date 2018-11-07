package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.entities.product.OrderProduct;
import javafx.util.Pair;

public class DiscountPriceAndQuantityStrategy extends DiscountStrategy implements CouponStrategy {
    @Override
    public Pair<String,Float> checkCouponThreshold(Order order, Coupon coupon) {
        if (    order.getPrice() >= coupon.getThresholdPrice() &&
                order.getOrderProductList().stream().mapToLong(OrderProduct::getAmount).sum() >= coupon.getThresholdQuantity()
        ) return super.checkDiscountType(coupon);
        return null;
    }
}
