package com.example.shopping.services.order;

import com.example.shopping.entities.Order;
import com.example.shopping.entities.OrderProduct;
import com.example.shopping.entities.Product;
import com.example.shopping.entities.User;
import com.example.shopping.repositories.OrderRepository;
import com.example.shopping.services.user.UserService;
import org.assertj.core.data.Index;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    private OrderService orderService;

    private OrderService orderServiceSpy;

    @Mock
    private UserService userService;

    @Mock
    private OrderRepository orderRepository;

    private User user;
    private Order order;
    private OrderProduct orderProduct = new OrderProduct().setOrder(this.order).setProduct(
            new Product().setName("Water").setPrice(10F)
    ).setAmount(2).setPrice(10F);

    @Before
    public void setUp() {
        orderService =  new OrderServiceImpl(userService, orderRepository);
        orderServiceSpy = Mockito.spy(orderService);

        this.user = new User().setUsername("paiizz").setPassword("1234");
        this.order = new Order().setUser(this.user).setOrderProductList(
                Arrays.asList(
                        new OrderProduct().setOrder(this.order).setProduct(
                                new Product().setName("Pizza").setPrice(299F)
                        ).setAmount(2).setPrice(299F),
                        new OrderProduct().setOrder(this.order).setProduct(
                                new Product().setName("Water").setPrice(10F)
                        ).setAmount(2).setPrice(10F)
                )
        )
                .setPrice(618F)
                .setDiscount(0F);
    }

    @Test
    public void createOrder() {
        //Arrange
        when(userService.getUserById(any())).thenReturn(this.user);
        doReturn(1L).when(orderServiceSpy).getCountOrderByUserId(any());
        when(orderRepository.save(any())).thenReturn(this.order);

        //Act
        Order orderResponse = orderService.createOrder(anyLong());

        //Assert
        assertThat(orderResponse.getUser().getUsername()).isEqualTo("paiizz");
        assertThat(orderResponse.getUser().getPassword()).isEqualTo("1234");

        assertThat(orderResponse.getOrderProductList().get(0).getProduct().getName()).isEqualTo("Pizza");
        assertThat(orderResponse.getOrderProductList().get(0).getProduct().getPrice()).isEqualTo(299F);
        assertThat(orderResponse.getOrderProductList().get(0).getAmount()).isEqualTo(2);
        assertThat(orderResponse.getOrderProductList().get(0).getPrice()).isEqualTo(299F);

        assertThat(orderResponse.getOrderProductList().get(1).getProduct().getName()).isEqualTo("Water");
        assertThat(orderResponse.getOrderProductList().get(1).getProduct().getPrice()).isEqualTo(10F);
        assertThat(orderResponse.getOrderProductList().get(1).getAmount()).isEqualTo(2);
        assertThat(orderResponse.getOrderProductList().get(1).getPrice()).isEqualTo(10F);

        assertThat(orderResponse.getPrice()).isEqualTo(618F);
        assertThat(orderResponse.getDiscount()).isEqualTo(0F);

        verify(orderRepository, times(1)).save(any());

    }

    @Test
    public void getOrderByUserId() {
    }

    @Test
    public void getCountOrderByUserId() {
    }
}