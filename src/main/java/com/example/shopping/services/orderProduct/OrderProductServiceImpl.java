package com.example.shopping.services.orderProduct;

import com.example.shopping.wrappers.CreateOrderRequest;
import com.example.shopping.entities.Order;
import com.example.shopping.entities.OrderProduct;
import com.example.shopping.entities.Product;
import com.example.shopping.repositories.OrderProductRepository;
import com.example.shopping.services.order.OrderServiceImpl;
import com.example.shopping.services.product.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.shopping.configs.constant.OrderConstants.DISCOUNT_PERCENT;
import static com.example.shopping.configs.constant.OrderConstants.DISCOUNT_THRESHOLD;

@Slf4j
@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private OrderServiceImpl orderServiceImpl;

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
            order.setPrice(order.getPrice() + (orderProduct.getAmount() * orderProduct.getPrice()) );
            if(orderServiceImpl.getCountOrderByUserId(order.getUser().getId()) > DISCOUNT_THRESHOLD ){
                order.setDiscount(DISCOUNT_PERCENT);
            }
            orderProductRepository.save(orderProduct);
        }
        return order;
    }
}
