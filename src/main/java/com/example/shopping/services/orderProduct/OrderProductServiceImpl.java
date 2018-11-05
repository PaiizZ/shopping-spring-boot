package com.example.shopping.services.orderProduct;

import com.example.shopping.services.product.ProductService;
import com.example.shopping.wrappers.CreateOrderRequest;
import com.example.shopping.entities.product.Order;
import com.example.shopping.entities.product.OrderProduct;
import com.example.shopping.entities.product.Product;
import com.example.shopping.repositories.product.OrderProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderProductServiceImpl implements OrderProductService {

    private ProductService productServiceImpl;

    private final OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository, ProductService productServiceImpl) {
        this.orderProductRepository = orderProductRepository;
        this.productServiceImpl = productServiceImpl;
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
            order.setPrice(order.getPrice() + (orderProduct.getAmount() * orderProduct.getPrice()) );
            orderProductRepository.save(orderProduct);
        }
        return order;
    }
}
