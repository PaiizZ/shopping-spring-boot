package com.example.shopping.services.order;

import com.example.shopping.entities.shopping.Order;
import com.example.shopping.repositories.OrderRepository;
import com.example.shopping.services.user.UserService;
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
    public Order createOrder(Long user_id) {
        Order order = new Order().setUser(userServiceImpl.getUserById(user_id)).setPrice(0F).setDiscount(0F);
        if (this.getCountOrderByUserId(order.getUser().getId()) > DISCOUNT_THRESHOLD ) {
            order.setDiscount(DISCOUNT_PERCENT);
        }
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }


    public Long getCountOrderByUserId(Long id){
        return orderRepository.countByUserId(id);
    }

}
