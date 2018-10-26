package com.example.shopping.services.order;

import com.example.shopping.entity.Order;
import com.example.shopping.entity.User;
import com.example.shopping.repositories.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public Order updateOrder(Long id, User user) {
        return null;
    }

    @Override
    public Order deleteOrder(Long id) {
        return null;
    }

}
