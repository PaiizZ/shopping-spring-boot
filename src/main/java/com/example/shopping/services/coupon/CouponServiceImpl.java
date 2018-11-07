package com.example.shopping.services.coupon;

import com.example.shopping.coupon.CouponStrategy;
import com.example.shopping.coupon.CouponStrategyFactory;
import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.repositories.coupon.CouponRepository;
import com.example.shopping.services.order.OrderService;
import com.example.shopping.wrappers.CreateOrderRequest;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private OrderService orderServiceImpl;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Order setBahtDiscount(Order order, CreateOrderRequest createOrderRequest) {
        Coupon coupon = couponRepository.findByCode(createOrderRequest.getCodeDiscount()).orElseThrow(() -> new RuntimeException());
        CouponStrategyFactory couponStrategyFactory = new CouponStrategyFactory();
        CouponStrategy couponStrategy = couponStrategyFactory.createCouponStategy(coupon.getType());
        Pair<String,Float> discount = couponStrategy.checkCouponThreshold(order, coupon);
        if (discount.getKey().equals("Price")) order.setBahtDiscount(discount.getValue());
        else order.setPercentDiscount(discount.getValue());
        orderServiceImpl.updateOrder(order.getId(),order);
        return order;
    }
}
