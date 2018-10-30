package com.example.shopping.wrappers;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<ProductRequest> productRequestList;

    @Data
    @Accessors(chain = true)
    public static class ProductRequest {
        private Long id;
        private Integer amount;
    }
}
