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

    public CouponServiceImpl(CouponRepository couponRepository, OrderService orderService) {
        this.couponRepository = couponRepository;
        this.orderService = orderService;
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Order setBahtDiscount(Order order, CreateOrderRequest createOrderRequest) {
        Coupon coupon = couponRepository.findByCode(createOrderRequest.getCodeDiscount()).orElseThrow(() -> new RuntimeException());
        CouponStrategyFactory couponStrategyFactory = new CouponStrategyFactory();
        CouponStrategy couponStrategy = couponStrategyFactory.createCouponStategy(coupon.getType());
        Pair<String,Float> discount = couponStrategy.applyCoupon(order, coupon);
        order.setDiscountByType(discount);
        orderService.updateOrder(order.getId(),order);
        return order;
    }
}
