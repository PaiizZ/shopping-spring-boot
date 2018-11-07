package com.example.shopping.coupon;

import static com.example.shopping.configs.constant.OrderConstants.COUPON_PRICE;
import static com.example.shopping.configs.constant.OrderConstants.COUPON_PRICE_QUANTITY;
import static com.example.shopping.configs.constant.OrderConstants.COUPON_QUANTITY;

public class CouponStrategyFactory {
    public CouponStrategy createCouponStategy(String couponType){
        switch (couponType) {
            case COUPON_PRICE: return new DiscountPriceStrategy();
            case COUPON_QUANTITY: return new DiscountQuantityStrategy();
            case COUPON_PRICE_QUANTITY: return new DiscountPriceAndQuantityStrategy();
            default: return null;
        }
    }
}
