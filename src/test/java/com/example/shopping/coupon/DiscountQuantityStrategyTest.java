package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import com.example.shopping.entities.product.OrderProduct;
import com.example.shopping.entities.product.Product;
import com.example.shopping.entities.product.User;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscountQuantityStrategyTest {
    private CouponStrategy couponStrategy;
    private CouponStrategy couponStrategySpy;

    @Mock
    private DiscountStrategy discountStrategy;

    private User user;
    private Order order;
    private OrderProduct orderProductPizza, orderProductWater;

    @Before
    public void setup() {
        couponStrategy = new DiscountQuantityStrategy();
        couponStrategySpy = Mockito.spy(couponStrategy);

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
                .setPrice(339F)
                .setPercentDiscount(0F);
    }

    @Test
    public void checkCouponThresholdSuccessfully() {
        Boolean result = couponStrategy.checkCouponThreshold(this.order,new Coupon().setThresholdQuantity(3F));

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void checkCouponThresholdFailed() {
        Boolean result = couponStrategy.checkCouponThreshold(this.order,new Coupon().setThresholdQuantity(4F));

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void applyCoupon() {
        Coupon coupon = new Coupon().setDiscountType("Baht").setDiscount(200F).setThresholdQuantity(5F);
//        Pair<String,Float> discount = new Pair<>("Baht",100F);

        doReturn(true).when(couponStrategySpy).checkCouponThreshold(any(),any());
//        when(discountStrategy.getDiscountByType(any())).thenReturn(discount);

        Pair<String, Float> result = couponStrategySpy.applyCoupon(order, coupon);

        assertThat(result.getKey()).isEqualTo("Baht");
        assertThat(result.getValue()).isEqualTo(200F);
    }
}