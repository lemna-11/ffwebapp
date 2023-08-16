package com.example.ffwebapp.middleware.entities;

public class singleorder {
    private static Order order = new Order();
    private singleorder(){

    }

    public static Order getOrder() {
        if(order == null) {
            order = new Order();
        }
        return order;
    }

    public static void setOrder(Order newOrder) {
        order = newOrder;
    }

}
