package com.example.ffwebapp.middleware.callers;

import com.example.ffwebapp.middleware.entities.ProductCategory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryCaller {
    private final RestTemplate restTemplate;
    private final String baseURI;

    public CategoryCaller(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.baseURI = "http://localhost:8080/productcategory";
    }

    public Long create(String name){
        Map<String, String> vars = new HashMap<>(1);
        vars.put("name", name);
        return restTemplate.postForObject(baseURI + "/create", null, Long.class, vars);
    }

    public Long update(Long id, String name){
        Map<String, Object> vars = new HashMap<>(2);
        vars.put("id", id);
        vars.put("name", name);
        return restTemplate.postForObject(baseURI + "/update", null, Long.class, vars);
    }

    public ProductCategory read(Long id){
        Map<String, Long> vars = new HashMap<>(1);
        vars.put("id", id);
        return restTemplate.getForEntity(baseURI + "/read", ProductCategory.class, vars).getBody();
    }

    public void delete(Long id){
        Map<String, Long> vars = new HashMap<>(1);
        vars.put("id", id);
        restTemplate.delete(baseURI + "/delete", vars);
    }
}
