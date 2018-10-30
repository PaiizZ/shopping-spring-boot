package com.example.shopping.services.orderProduct;

import com.example.shopping.entities.Order;
import com.example.shopping.entities.OrderProduct;
import com.example.shopping.entities.Product;
import com.example.shopping.entities.User;
import com.example.shopping.repositories.OrderProductRepository;
import com.example.shopping.repositories.ProductRepository;
import com.example.shopping.services.product.ProductService;
import com.example.shopping.services.product.ProductServiceImpl;
import com.example.shopping.wrappers.CreateOrderRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderProductServiceImplTest {
    private OrderProductService orderProductService;
    private ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderProductRepository orderProductRepository;

    @Before
    public void setUp() throws Exception {
        orderProductService = new OrderProductServiceImpl(orderProductRepository);
        productService = new ProductServiceImpl(productRepository);

    }

    @Test
    public void createOrderProduct() {
//        //Arrange
//        Product product1 = new Product().setName("Water").setPrice(10F);
//        Product product2 = new Product().setName("Pizza").setPrice(299F);
//        productService.createProduct(product1);
//        productService.createProduct(product2);
//        when(productRepository.save(any(Product.class))).thenReturn(product1);
//        when(productRepository.save(any(Product.class))).thenReturn(product2);
//
//        // --------------------------------------------------
//
//        User user = new User().setUsername("paiizz").setPassword("1234");
//
//        Order order = new Order().setUser(user).setPrice(20F).setDiscount(0F);
//
//        Product product = new Product().setName("Water").setPrice(10F);
//
//        OrderProduct orderProduct = new OrderProduct();
//        orderProduct.setOrder(order).setProduct(product).setAmount(2).setPrice(10F);
//
//        when(orderProductRepository.save(any(OrderProduct.class))).thenReturn(orderProduct);
//
//        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
//        createOrderRequest.setProductRequestList(
//                Arrays.asList(
//                        new CreateOrderRequest.ProductRequest().setId(1L).setAmount(2),
//                        new CreateOrderRequest.ProductRequest().setId(2L).setAmount(1)
//                )
//        );
//
//        //Act
//        Order orderProductResponse = orderProductService.createOrderProduct(order, createOrderRequest);
//
//        //Assert
//        assertThat(orderProductResponse.getUser().getUsername()).isEqualTo("paiizz");
//        assertThat(orderProductResponse.getUser().getPassword()).isEqualTo("1234");
//
//        verify(orderProductRepository, times(1)).save(any(OrderProduct.class));
    }
}