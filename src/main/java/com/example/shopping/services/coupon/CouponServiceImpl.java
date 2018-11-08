package com.example.shopping.services.coupon;

import com.example.shopping.coupon.CouponStrategy;
import com.example.shopping.coupon.CouponStrategyFactory;
import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.repositories.coupon.CouponRepository;
import com.example.shopping.services.order.OrderService;
import com.example.shopping.wrappers.CreateOrderRequest;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {
    private CouponRepository couponRepository;

    private OrderService orderService;

    private CouponStrategyFactory couponStrategyFactory;

    public CouponServiceImpl(CouponRepository couponRepository, OrderService orderService, CouponStrategyFactory couponStrategyFactory) {
        this.couponRepository = couponRepository;
        this.orderService = orderService;
        this.couponStrategyFactory = couponStrategyFactory;
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Order setBahtDiscount(Order order, CreateOrderRequest createOrderRequest) {
        Coupon coupon = couponRepository.findByCode(createOrderRequest.getCodeDiscount()).orElseThrow(() -> new RuntimeException());
        CouponStrategy couponStrategy = couponStrategyFactory.createCouponStrategy(coupon.getType());
        Pair<String,Float> discount = couponStrategy.applyCoupon(order, coupon);
        order.setDiscountByType(discount);
        orderService.updateOrder(order.getId(),order);
        return order;
    }
}
