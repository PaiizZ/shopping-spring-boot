package com.example.shopping.services.coupon;

import com.example.shopping.entities.coupon.Coupon;
import com.example.shopping.repositories.coupon.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

//    public CouponServiceImpl(CouponRepository couponRepository) {
//        this.couponRepository = couponRepository;
//    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }
}
