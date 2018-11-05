package com.example.shopping.services.orderProduct;

import com.example.shopping.entities.product.Order;
import com.example.shopping.entities.product.OrderProduct;
import com.example.shopping.entities.product.Product;
import com.example.shopping.entities.product.User;
import com.example.shopping.repositories.product.OrderProductRepository;
import com.example.shopping.services.coupon.CouponService;
import com.example.shopping.services.product.ProductService;
import com.example.shopping.wrappers.CreateOrderRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderProductServiceImplTest {
    private OrderProductService orderProductService;

    @Mock
    private ProductService productService;

    @Mock
    private CouponService couponService;

    @Mock
    private OrderProductRepository orderProductRepository;

    @Before
    public void setUp() {
        orderProductService = new OrderProductServiceImpl(orderProductRepository, productService, couponService);
    }

    @Test
    public void createOrderProduct() {
        //Arrange
        Product product1 = new Product().setName("Water").setPrice(10F);
        Product product2 = new Product().setName("Pizza").setPrice(199F);

        when(productService.getProductById(1L)).thenReturn(product1);
        when(productService.getProductById(2L)).thenReturn(product2);

        // --------------------------------------------------

        User user = new User().setUsername("paiizz").setPassword("1234");

        Order order = new Order().setUser(user).setPrice(20F).setPercentDiscount(0F);

        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setOrder(order).setProduct(product1).setAmount(2).setPrice(20F);

        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setOrder(order).setProduct(product1).setAmount(1).setPrice(199F);

        when(orderProductRepository.save(any(OrderProduct.class))).thenReturn(orderProduct1);
        when(orderProductRepository.save(any(OrderProduct.class))).thenReturn(orderProduct2);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setProductRequestList(
                Arrays.asList(
                        new CreateOrderRequest.ProductRequest().setId(1L).setAmount(2),
                        new CreateOrderRequest.ProductRequest().setId(2L).setAmount(1)
                )
        );

        //Act
        Order orderProductResponse = orderProductService.createOrderProduct(order, createOrderRequest);

        //Assert
        assertThat(orderProductResponse.getUser().getUsername()).isEqualTo("paiizz");
        assertThat(orderProductResponse.getUser().getPassword()).isEqualTo("1234");

        verify(orderProductRepository, times(2)).save(any(OrderProduct.class));
    }
}