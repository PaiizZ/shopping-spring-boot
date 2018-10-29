package com.example.shopping.services.orderProduct;

import com.example.shopping.Wrapper.CreateOrderRequest;
import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderProduct;

import java.util.List;

public interface OrderProductService {
    Order createOrderProduct(Order order, CreateOrderRequest createOrderRequest);
//    OrderProduct getOrderProductById(Long id);
//    List<OrderProduct> getAllOrderProduct();
//    OrderProduct updateOrderProduct(Long id, OrderProduct orderProduct);
//    OrderProduct deleteOrderProduct(Long id);
}
