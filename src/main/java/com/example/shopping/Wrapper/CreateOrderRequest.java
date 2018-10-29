package com.example.shopping.Wrapper;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private List<ProductRequest> productRequestList;

    @Data
    public static class ProductRequest {
        private Long id;
        private Integer amount;
    }
}
