package com.example.shopping.services.order;

import com.example.shopping.entities.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long user_id);
    List<Order> getOrderByUserId(Long id);
//    List<Order> getAllOrder();
//    Order updateOrder(Long id, Order order);
//    Order deleteOrder(Long id);
}
