package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import javafx.util.Pair;

public abstract class DiscountStrategy {
    Pair<String,Float> getDiscountByType(Coupon coupon) {
        if(coupon.getDiscountType().equals("Baht")) return new Pair<>(coupon.getDiscountType(),coupon.getDiscount());
        return new Pair<>(coupon.getDiscountType(),coupon.getDiscount() / 100F);
    }
}
