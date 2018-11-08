package com.example.shopping.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.entities.product.Order;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DiscountPriceStrategyTest {
    private CouponStrategy couponStrategy;
    private CouponStrategy couponStrategySpy;

    @Mock
    private DiscountStrategy discountStrategy;

    @Before
    public void setup() {
        couponStrategy = new DiscountPriceStrategy();
        couponStrategySpy = Mockito.spy(couponStrategy);
    }


    @Test
    public void checkCouponThresholdSuccessfully() {
        Boolean result = couponStrategy.checkCouponThreshold(new Order().setPrice(500F),new Coupon().setThresholdPrice(500F));

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void checkCouponThresholdFailed() {
        Boolean result = couponStrategy.checkCouponThreshold(new Order().setPrice(400F),new Coupon().setThresholdPrice(500F));

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void applyCoupon() {
        Order order = new Order().setPrice(500F);
        Coupon coupon = new Coupon().setDiscountType("Baht").setDiscount(200F).setThresholdPrice(500F);
        Pair<String,Float> discount = new Pair<>("Baht",100F);

        doReturn(true).when(couponStrategySpy).checkCouponThreshold(any(),any());
        when(discountStrategy.getDiscountByType(any())).thenReturn(discount);

        Pair<String, Float> result = couponStrategySpy.applyCoupon(order, coupon);

        assertThat(result.getKey()).isEqualTo("Baht");
        assertThat(result.getValue()).isEqualTo(200F);

    }
}