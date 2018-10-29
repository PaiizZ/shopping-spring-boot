package com.example.shopping.services.order;

import com.example.shopping.entity.Order;
import com.example.shopping.exception.OrderNotFoundException;
import com.example.shopping.repositories.OrderRepository;
import com.example.shopping.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private UserServiceImpl userServiceImpl;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Long user_id) {
        Order order = new Order().setUser(userServiceImpl.getUserById(user_id)).setNet(0F).setDiscount(0F);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found."));
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        return orderRepository.findById(id).map(it -> {
            it.setOrderProductList(order.getOrderProductList());
            it.setDiscount(order.getDiscount());
            it.setNet(order.getNet());
            return orderRepository.save(it);
        }).orElseThrow(() -> new OrderNotFoundException("Order Not Found."));
    }

    @Override
    public Order deleteOrder(Long id) {
        return orderRepository.findById(id).map(it -> {
            orderRepository.delete(it);
            return it;
        }).orElseThrow(() -> new OrderNotFoundException("Order Not Found."));
    }

}
