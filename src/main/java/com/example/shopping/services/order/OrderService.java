package com.example.shopping.services.order;

import com.example.shopping.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrder();
    Order updateOrder(Long id, Order order);
    Order deleteOrder(Long id);
}
