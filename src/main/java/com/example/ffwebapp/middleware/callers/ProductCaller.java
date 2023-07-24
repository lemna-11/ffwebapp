package com.example.ffwebapp.middleware.callers;

import com.example.ffwebapp.middleware.entities.Product;
import com.example.ffwebapp.middleware.entities.ProductCategory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductCaller {
    private final RestTemplate restTemplate;
    private final String baseURI;

    public ProductCaller(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.baseURI = "http://localhost:8080/product";
    }

    public Long create(String name, ProductCategory category, Long price){
        Map<String, Object> vars = new HashMap<>(3);
        vars.put("name", name);
        vars.put("category", category);
        vars.put("price", price);
        return restTemplate.postForObject(baseURI + "/create?name={name}&category={category}&price={price}", null, Long.class, vars);
    }

    public Long update(Long id, String name, ProductCategory category, Long price) {
        Map<String, Object> vars = new HashMap<>(4);
        vars.put("id", id);
        vars.put("name", name);
        vars.put("category", category);
        vars.put("price", price);
        return restTemplate.postForObject(baseURI + "/update?id={id}&name={name}&category={category}&price={price}", null, Long.class, vars);
    }

    public Product read(Long id) {
        Map<String, Object> vars = new HashMap<>(1);
        vars.put("id", id);
        return restTemplate.getForEntity(baseURI + "/read?id={id}", Product.class, vars).getBody();
    }

    public void delete(Long id){
        Map<String, Object> vars = new HashMap<>(1);
        vars.put("id", id);
        restTemplate.delete(baseURI + "/delete?id={id}", vars);
    }
}
