package com.example.shopping.coupon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.example.shopping.configs.constant.OrderConstants.COUPON_PRICE;
import static com.example.shopping.configs.constant.OrderConstants.COUPON_PRICE_QUANTITY;
import static com.example.shopping.configs.constant.OrderConstants.COUPON_QUANTITY;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CouponStrategyFactoryTest {

    private CouponStrategyFactory couponStrategyFactory;
    
    @Before
    public void setup() {
        this.couponStrategyFactory = new CouponStrategyFactory();
    }
    
    @Test
    public void createCouponStrategySuccessfullyByCouponTypePrice() {
        CouponStrategy getFromFactory = couponStrategyFactory.createCouponStrategy(COUPON_PRICE);

        assertThat(getFromFactory.getClass()).isEqualTo(DiscountPriceStrategy.class);
    }

    @Test
    public void createCouponStrategySuccessfullyByCouponTypeQuantity() {
        CouponStrategy getFromFactory = couponStrategyFactory.createCouponStrategy(COUPON_QUANTITY);

        assertThat(getFromFactory.getClass()).isEqualTo(DiscountQuantityStrategy.class);
    }

    @Test
    public void createCouponStrategySuccessfullyByCouponTypePriceAndQuantity() {
        CouponStrategy getFromFactory = couponStrategyFactory.createCouponStrategy(COUPON_PRICE_QUANTITY);

        assertThat(getFromFactory.getClass()).isEqualTo(DiscountPriceAndQuantityStrategy.class);
    }
}