package com.example.shopping.services.order;

import com.example.shopping.entities.product.Order;
import com.example.shopping.exceptions.OrderNotFoundException;
import com.example.shopping.repositories.product.OrderRepository;
import com.example.shopping.services.user.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.shopping.configs.constant.OrderConstants.DISCOUNT_PERCENT;
import static com.example.shopping.configs.constant.OrderConstants.DISCOUNT_THRESHOLD;

@Service
public class OrderServiceImpl implements OrderService{

    private UserService userServiceImpl;
    private final OrderRepository orderRepository;
    public OrderServiceImpl(UserService userServiceImpl, OrderRepository orderRepository) {
        this.userServiceImpl = userServiceImpl;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Long userId) {
        Order order = new Order().setUser(userServiceImpl.getUserById(userId)).setPrice(0F).setPercentDiscount(0F).setBahtDiscount(0F);
        if (this.getCountOrderByUserId(order.getUser().getId()) > DISCOUNT_THRESHOLD ) {
            order.setPercentDiscount(DISCOUNT_PERCENT);
        }
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        return orderRepository.findById(id).map(it -> {
            it.setBahtDiscount(order.getBahtDiscount());
            return orderRepository.save(it);
        }).orElseThrow(() -> new OrderNotFoundException("Order Not Found."));
    }

    @Override
    public List<Order> getOrderByUserId(Long id) {
        List<Order> orderList = orderRepository.findAllByUserId(id);
        for (Order order : orderList) {
            order.getOrderProductList().size();
        }
        return orderList;
    }


    public Long getCountOrderByUserId(Long id){
        return orderRepository.countByUserId(id);
    }

}
