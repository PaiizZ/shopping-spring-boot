package com.example.shopping.entities.coupon;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String code;

    @Column
    private String type;

    @Column
    private Float threshold;

    @Column
    private Float price;
}
