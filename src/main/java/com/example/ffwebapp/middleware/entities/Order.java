package com.example.ffwebapp.middleware.entities;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Long id;
    private List<Product> products;
    private OrderStatus status;

    public Long getPriceInCents(){
        return products.stream()
                .map(Product::getPriceInCents)
                .reduce(Long::sum)
                .orElse(0L);
    }
}
