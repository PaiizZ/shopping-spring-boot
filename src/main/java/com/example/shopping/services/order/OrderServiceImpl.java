package com.example.shopping.services.order;

import com.example.shopping.entity.Order;
import com.example.shopping.repositories.OrderRepository;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

}
