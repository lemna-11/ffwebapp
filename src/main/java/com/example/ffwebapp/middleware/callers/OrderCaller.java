package com.example.ffwebapp.middleware.callers;

import com.example.ffwebapp.middleware.entities.Order;
import com.example.ffwebapp.middleware.entities.OrderStatus;
import com.example.ffwebapp.middleware.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderCaller {
    private final RestTemplate restTemplate;
    private final String baseURI;

    public OrderCaller(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.baseURI = "http://localhost:8080/order";
    }

    public Long create(List<Product> products, OrderStatus status){
        Order order = new Order();
        order.setProducts(products);
        order.setStatus(status);
        return restTemplate.postForObject(baseURI + "/create", order, Long.class);
    }

    public Long update(Long id, List<Product> products, OrderStatus status){
        Order order = new Order();
        order.setId(id);
        order.setProducts(products);
        order.setStatus(status);
        return restTemplate.postForObject(baseURI + "/update", order, Long.class);
    }

    public Order read(Long id){
        Map<String, Long> vars = new HashMap<>();
        vars.put("id", id);
        return restTemplate.getForEntity(baseURI + "/read?id={id}", Order.class, vars).getBody();
    }

    public void delete(Long id){
        Map<String, Long> vars = new HashMap<>();
        vars.put("id", id);
        restTemplate.delete(baseURI + "/delete?id={id}", vars);
    }
}
