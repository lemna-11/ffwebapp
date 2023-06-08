package com.example.ffwebapp.middleware.entities;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private ProductCategory category;
    private Long priceInCents;
}