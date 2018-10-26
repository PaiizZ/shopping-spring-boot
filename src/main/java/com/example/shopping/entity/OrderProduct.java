package com.example.shopping.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;
//    private int amount;
//    private float price;
}
