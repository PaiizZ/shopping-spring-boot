package com.example.shopping.services.orderProduct;

import com.example.shopping.Wrapper.CreateOrderRequest;
import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderProduct;
import com.example.shopping.entity.Product;
import com.example.shopping.repositories.OrderProductRepository;
import com.example.shopping.services.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public Order createOrderProduct(Order order, CreateOrderRequest createOrderRequest) {
        for (CreateOrderRequest.ProductRequest productRequest : createOrderRequest.getProductRequestList()) {
            OrderProduct orderProduct = new OrderProduct();
            Product product = productServiceImpl.getProductById(productRequest.getId());
            orderProduct
                    .setOrder(order)
                    .setProduct(product)
                    .setAmount(productRequest.getAmount())
                    .setPrice(product.getPrice());
            order.setNet(order.getNet() + (orderProduct.getAmount() * orderProduct.getPrice()) );
            orderProductRepository.save(orderProduct);
        }
        return order;
    }
}
