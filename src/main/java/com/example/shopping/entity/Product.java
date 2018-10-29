package com.example.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToMany
    @JoinColumn(name = "order_product_id")
    @JsonIgnore
    private List<OrderProduct> orderProductList;

    @Column
    private String name;

    @Column
    private float price;
}
