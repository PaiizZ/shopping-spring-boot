package com.example.shopping.entities.product;

import com.example.shopping.entities.coupon.Coupon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.util.Pair;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    @JsonProperty("order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderProduct> orderProductList;

    @Column
    private Float price;

    @Column
    private Float percentDiscount;

    @Column
    private Float bahtDiscount;
    
    public void setDiscountByType(Pair<String,Float> discount){
        if (discount.getKey().equals("Price")) this.setBahtDiscount(discount.getValue());
        else this.setPercentDiscount(discount.getValue());
    }
}
