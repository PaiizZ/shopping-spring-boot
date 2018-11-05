package com.example.shopping.services.order;

import com.example.shopping.entities.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long user_id);
    List<Order> getOrderByUserId(Long id);
    Long getCountOrderByUserId(Long id);
}
