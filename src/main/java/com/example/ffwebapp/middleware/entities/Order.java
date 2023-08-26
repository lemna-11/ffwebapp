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
    public String receipt() {
        StringBuilder sb = new StringBuilder("Order " + id + "\n");

        long totalprice = (long) 0;

        for (Product p : products) {
            sb.append(p.getName() + " --- " + p.getPriceInCents() + " cents\n");
            totalprice += p.getPriceInCents();
        }
        sb.append("Price: " + totalprice + " cents\n Enjoy your meal :3");
        return sb.toString();
    }
}
