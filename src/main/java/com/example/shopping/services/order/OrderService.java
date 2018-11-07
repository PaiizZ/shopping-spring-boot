package com.example.shopping.services.order;

import com.example.shopping.entities.product.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long user_id);
    Order updateOrder(Long id, Order order);
    List<Order> getOrderByUserId(Long id);
    Long getCountOrderByUserId(Long id);
}
