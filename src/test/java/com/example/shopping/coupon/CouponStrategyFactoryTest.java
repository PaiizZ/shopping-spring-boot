package com.example.shopping.coupon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.example.shopping.configs.constant.OrderConstants.COUPON_PRICE;
import static com.example.shopping.configs.constant.OrderConstants.COUPON_PRICE_QUANTITY;
import static com.example.shopping.configs.constant.OrderConstants.COUPON_QUANTITY;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CouponStrategyFactoryTest {
    
    @Mock
    private DiscountPriceStrategy discountPriceStrategy;
    
    @Mock
    private DiscountQuantityStrategy discountQuantityStrategy;
    
    @Mock
    private DiscountPriceAndQuantityStrategy discountPriceAndQuantityStrategy;
    
    private CouponStrategyFactory couponStrategyFactory;
    
    @Before
    public void setup() {
        this.couponStrategyFactory = new CouponStrategyFactory();
    }
    
    @Test
    public void createCouponStrategySuccessfullyByCouponType() {
        //Arrange
//        when(couponStrategyFactory.createCouponStrategy(anyString())).thenReturn(discountPriceStrategy);
//        when(couponStrategyFactory.createCouponStrategy(COUPON_QUANTITY)).thenReturn(discountQuantityStrategy);
//        when(couponStrategyFactory.createCouponStrategy(COUPON_PRICE_QUANTITY)).thenReturn(discountPriceAndQuantityStrategy);
        
        //Act
        
    }
}