package com.example.shopping.services.orderProduct;

import com.example.shopping.wrappers.CreateOrderRequest;
import com.example.shopping.entities.shopping.Order;
public interface OrderProductService {
    Order createOrderProduct(Order order, CreateOrderRequest createOrderRequest);
}
