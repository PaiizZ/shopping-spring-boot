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
        this.couponService = new CouponServiceImpl(couponRepository,orderService);
        this.coupon = new Coupon().setId(1L).setCode("SME").setType("Price").setDiscountType("Price").setDiscount(200F).setThresholdQuantity(5F);

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
                .setPercentDiscount(0F);
    }

    @Test
    public void createCoupon() {
        //Arrange
        when(couponRepository.save(any())).thenReturn(this.coupon);

        //Act
        Coupon couponResponse = this.couponService.createCoupon(any());

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
       


    }
}