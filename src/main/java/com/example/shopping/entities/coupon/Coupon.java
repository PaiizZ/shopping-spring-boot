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
    private Float type;

    @Column
    private Float condition;

    @Column
    private Float price;
}
