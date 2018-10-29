package com.example.shopping.controllers;

import com.example.shopping.Wrapper.CreateOrderRequest;
import com.example.shopping.entity.Order;
import com.example.shopping.services.order.OrderServiceImpl;
import com.example.shopping.services.orderProduct.OrderProductService;
import com.example.shopping.services.orderProduct.OrderProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderProductServiceImpl orderProductServiceImpl;

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping("{id}")
    public ResponseEntity<Order> createOrder(@PathVariable(value = "id") Long user_id, @RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderServiceImpl.createOrder(user_id);
        orderProductServiceImpl.createOrderProduct(order, createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
