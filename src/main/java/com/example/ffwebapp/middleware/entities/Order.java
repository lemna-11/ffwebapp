package com.example.ffwebapp.middleware.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Long id;
    private List<Product> products = new ArrayList<>();
    private OrderStatus status = OrderStatus.IN_PROCESS;

    public Long getPriceInCents(){
        return products.stream()
                .map(Product::getPriceInCents)
                .reduce(Long::sum)
                .orElse(0L);
    }
}
