package com.example.shopping.services.order;

import com.example.shopping.entities.shopping.Order;
import com.example.shopping.entities.shopping.OrderProduct;
import com.example.shopping.entities.shopping.Product;
import com.example.shopping.entities.shopping.User;
import com.example.shopping.repositories.OrderRepository;
import com.example.shopping.services.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private OrderProduct orderProductPizza, orderProductWater, orderProductChocolate;
    private List<Order> orderList = new ArrayList<>();

    @Before
    public void setUp() {
        orderService =  new OrderServiceImpl(userService, orderRepository);
        orderServiceSpy = Mockito.spy(orderService);

        this.user = new User().setUsername("paiizz").setPassword("1234");

        this.orderProductPizza = new OrderProduct().setOrder(this.order).setProduct(
                new Product().setName("Pizza").setPrice(299F)
        ).setAmount(1).setPrice(299F);

        this.orderProductWater = new OrderProduct().setOrder(this.order).setProduct(
                new Product().setName("Water").setPrice(10F)
        ).setAmount(2).setPrice(10F);

        this.order = new Order().setUser(this.user).setOrderProductList(
                Arrays.asList(
                        this.orderProductPizza,
                        this.orderProductWater
                )
        )
                .setPrice(618F)
                .setDiscount(0F);

        this.orderList.add(this.order);
    }

    @Test
    public void createOrder() {
        //Arrange
        when(userService.getUserById(any())).thenReturn(this.user);
        doReturn(4L).when(orderServiceSpy).getCountOrderByUserId(any());
        when(orderRepository.save(any())).thenReturn(this.order);

        //Act
        Order orderResponse = orderServiceSpy.createOrder(anyLong());

        //Assert
        assertThat(orderRepository.save(any(Order.class))).isEqualTo(orderResponse);

        assertThat(orderResponse).isNotNull();

        assertThat(orderResponse.getUser().getUsername()).isEqualTo("paiizz");
        assertThat(orderResponse.getUser().getPassword()).isEqualTo("1234");

        assertThat(orderResponse.getOrderProductList()).contains(this.orderProductPizza);
        assertThat(orderResponse.getOrderProductList()).contains(this.orderProductWater);

        assertThat(orderResponse.getOrderProductList().get(0).getProduct().getName()).isEqualTo("Pizza");
        assertThat(orderResponse.getOrderProductList().get(0).getProduct().getPrice()).isEqualTo(299F);
        assertThat(orderResponse.getOrderProductList().get(0).getAmount()).isEqualTo(1);
        assertThat(orderResponse.getOrderProductList().get(0).getPrice()).isEqualTo(299F);

        assertThat(orderResponse.getOrderProductList().get(1).getProduct().getName()).isEqualTo("Water");
        assertThat(orderResponse.getOrderProductList().get(1).getProduct().getPrice()).isEqualTo(10F);
        assertThat(orderResponse.getOrderProductList().get(1).getAmount()).isEqualTo(2);
        assertThat(orderResponse.getOrderProductList().get(1).getPrice()).isEqualTo(10F);

        assertThat(orderResponse.getPrice()).isEqualTo(618F);
        assertThat(orderResponse.getDiscount()).isEqualTo(0F);

        verify(orderRepository, times(2)).save(any());
    }

    @Test
    public void getOrderByUserIdSuccess() {
        //Arrange
        when(orderRepository.findAllByUserId(anyLong())).thenReturn(this.orderList);

        //Act
        List<Order> orderListResponse = orderService.getOrderByUserId(anyLong());

        //Assert
        assertThat(orderListResponse).isNotNull();

        assertThat(orderListResponse).contains(this.order);

        assertThat(orderListResponse.get(0).getUser()).isNotNull();
        assertThat(orderListResponse.get(0).getUser()).isEqualTo(this.user);
        assertThat(orderListResponse.get(0).getUser()).isEqualToComparingFieldByField(this.user);
        assertThat(orderListResponse.get(0).getUser()).isEqualToIgnoringNullFields(this.user);
        assertThat(orderListResponse.get(0).getUser().getUsername()).isEqualTo("paiizz");
        assertThat(orderListResponse.get(0).getUser().getPassword()).isEqualTo("1234");

        assertThat(orderListResponse.get(0).getOrderProductList()).contains(this.orderProductPizza);
        assertThat(orderListResponse.get(0).getOrderProductList()).contains(this.orderProductWater);

        assertThat(orderListResponse.get(0).getOrderProductList().get(0).getProduct().getName()).isEqualTo("Pizza");
        assertThat(orderListResponse.get(0).getOrderProductList().get(0).getProduct().getPrice()).isEqualTo(299F);
        assertThat(orderListResponse.get(0).getOrderProductList().get(0).getAmount()).isEqualTo(1);
        assertThat(orderListResponse.get(0).getOrderProductList().get(0).getPrice()).isEqualTo(299F);

        assertThat(orderListResponse.get(0).getOrderProductList().get(1).getProduct().getName()).isEqualTo("Water");
        assertThat(orderListResponse.get(0).getOrderProductList().get(1).getProduct().getPrice()).isEqualTo(10F);
        assertThat(orderListResponse.get(0).getOrderProductList().get(1).getAmount()).isEqualTo(2);
        assertThat(orderListResponse.get(0).getOrderProductList().get(1).getPrice()).isEqualTo(10F);

        assertThat(orderListResponse.get(0).getPrice()).isEqualTo(618F);
        assertThat(orderListResponse.get(0).getDiscount()).isEqualTo(0F);

        verify(orderRepository, times(1)).findAllByUserId(anyLong());

    }

    @Test
    public void getOrderByUserIdFailed() {
        //Arrange
        when(orderRepository.findAllByUserId(anyLong())).thenReturn(this.orderList);

        //Act
        List<Order> orderListResponse = orderService.getOrderByUserId(anyLong());

        //Assert
        assertThat(orderListResponse).isNotNull();
        assertThat(orderListResponse).isNotEqualTo(new ArrayList<Order>());
    }

    @Test
    public void getCountOrderByUserIdSuccess() {
        //Arrange
        when(orderRepository.countByUserId(anyLong())).thenReturn(3L);

        //Act
        Long count = orderService.getCountOrderByUserId(anyLong());

        //Assert
        assertThat(orderRepository.countByUserId(anyLong())).isEqualTo(count);

        assertThat(count).isEqualTo(3L);

        verify(orderRepository, times(2)).countByUserId(anyLong());

    }

    @Test
    public void getCountOrderByUserIdFailed() {
        //Arrange
        when(orderRepository.countByUserId(anyLong())).thenReturn(3L);

        //Act
        Long count = orderService.getCountOrderByUserId(anyLong());

        //Assert
        assertThat(count).isNotEqualTo(2L);

        verify(orderRepository, times(1)).countByUserId(anyLong());
    }
}