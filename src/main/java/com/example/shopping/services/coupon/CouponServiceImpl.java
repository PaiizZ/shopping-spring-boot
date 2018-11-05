package com.example.shopping.services.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.repositories.coupon.CouponRepository;
import com.example.shopping.wrappers.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.shopping.configs.constant.OrderConstants.COUPON_PRICE;


@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Order setDiscount(Order order, CreateOrderRequest createOrderRequest) {
        Coupon coupon = couponRepository.findByCode(createOrderRequest.getCodeDiscount()).orElseThrow(() -> new RuntimeException());
        if (coupon.getType().equals(COUPON_PRICE)) {

        } else {

        }
        return order;
    }
}
