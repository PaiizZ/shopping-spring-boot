package com.example.shopping.entities.shopping;

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

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderProduct> orderProductList;

    @Column
    private String name;

    @Column
    private Float price;
}
