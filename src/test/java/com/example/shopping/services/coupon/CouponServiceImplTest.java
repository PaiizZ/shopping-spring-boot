package com.example.shopping.services.coupon;

import com.example.shopping.coupon.CouponStrategyFactory;
import com.example.shopping.coupon.DiscountPriceStrategy;
import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.entities.product.OrderProduct;
import com.example.shopping.entities.product.Product;
import com.example.shopping.entities.product.User;
import com.example.shopping.repositories.coupon.CouponRepository;
import com.example.shopping.services.order.OrderService;
import com.example.shopping.wrappers.CreateOrderRequest;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CouponServiceImplTest {
    private CouponService couponService;
    private Coupon coupon;
    @Mock
    private CouponRepository couponRepository;

    @Mock
    private OrderService orderService;

    @Mock
    private CouponStrategyFactory couponStrategyFactory;

    @Mock
    private DiscountPriceStrategy discountPriceStrategy;

    private User user;
    private Order order;
    private OrderProduct orderProductPizza, orderProductWater;

    @Before
    public void setup(){
        this.couponService = new CouponServiceImpl(couponRepository,orderService,couponStrategyFactory);
        this.coupon = new Coupon().setId(1L).setCode("SME").setType("Price").setDiscountType("Price").setDiscount(200F).setThresholdQuantity(5F);

        this.user = new User().setUsername("paiizz").setPassword("1234");

        this.orderProductPizza = new OrderProduct().setOrder(this.order).setProduct(
                new Product().setName("Pizza").setPrice(299F)
        ).setAmount(1).setPrice(299F);

        this.orderProductWater = new OrderProduct().setOrder(this.order).setProduct(
                new Product().setName("Water").setPrice(10F)
        ).setAmount(5).setPrice(10F);

        this.order = new Order().setUser(this.user).setOrderProductList(
                Arrays.asList(
                        this.orderProductPizza,
                        this.orderProductWater
                )
        )
                .setPrice(618F)
                .setPercentDiscount(0F);
    }

    @Test
    public void createCoupon() {
        //Arrange
        when(couponRepository.save(any())).thenReturn(this.coupon);

        //Act
        Coupon couponResponse = couponService.createCoupon(any());

        //Assert
        assertThat(couponResponse).isNotNull();

        assertThat(couponResponse.getId()).isEqualTo(1L);
        assertThat(couponResponse.getType()).isEqualTo("Price");
        assertThat(couponResponse.getDiscountType()).isEqualTo("Price");
        assertThat(couponResponse.getDiscount()).isEqualTo(200F);
        assertThat(couponResponse.getThresholdQuantity()).isEqualTo(5F);

        verify(couponRepository, times(1)).save(any());
    }

    @Test
    public void setBahtDiscount() {
        //Arrange
        when(couponRepository.findByCode(any())).thenReturn(Optional.of(this.coupon));
        when(couponStrategyFactory.createCouponStrategy(any())).thenReturn(discountPriceStrategy);
        when(discountPriceStrategy.applyCoupon(any(),any())).thenReturn(new Pair<String, Float>("Price",200F));
        when(orderService.updateOrder(any(),any())).thenReturn(this.order);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setCodeDiscount("SME");
        createOrderRequest.setProductRequestList(
                Arrays.asList(
                        new CreateOrderRequest.ProductRequest().setId(1L).setAmount(1),
                        new CreateOrderRequest.ProductRequest().setId(2L).setAmount(5)
                )
        );

        //Act
        Order orderResponse = couponService.setBahtDiscount(this.order,createOrderRequest);

        //Assert
        assertThat(orderResponse).isNotNull();

        assertThat(orderResponse.getUser().getUsername()).isEqualTo("paiizz");
        assertThat(orderResponse.getUser().getPassword()).isEqualTo("1234");

        assertThat(orderResponse.getOrderProductList().get(0).getProduct().getName()).isEqualTo("Pizza");
        assertThat(orderResponse.getOrderProductList().get(0).getProduct().getPrice()).isEqualTo(299F);
        assertThat(orderResponse.getOrderProductList().get(0).getAmount()).isEqualTo(1);
        assertThat(orderResponse.getOrderProductList().get(0).getPrice()).isEqualTo(299F);

        assertThat(orderResponse.getOrderProductList().get(1).getProduct().getName()).isEqualTo("Water");
        assertThat(orderResponse.getOrderProductList().get(1).getProduct().getPrice()).isEqualTo(10F);
        assertThat(orderResponse.getOrderProductList().get(1).getAmount()).isEqualTo(5);
        assertThat(orderResponse.getOrderProductList().get(1).getPrice()).isEqualTo(10F);

        assertThat(orderResponse.getPercentDiscount()).isEqualTo(0F);
        assertThat(orderResponse.getBahtDiscount()).isEqualTo(200F);

        verify(couponRepository, times(1)).findByCode(any());
        verify(orderService, times(1)).updateOrder(any(),any());
    }
}