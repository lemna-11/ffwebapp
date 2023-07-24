package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.CategoryCaller;
import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.callers.ProductCaller;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("")
public class MainPage extends AppLayout {
    public MainPage(CategoryCaller categoryCaller, OrderCaller orderCaller, ProductCaller productCaller){
        addToNavbar(new H1("why"));
        Button toCheckout = new Button("Check out");
        toCheckout.addClickListener(e -> getUI().orElseThrow().navigate(CheckoutPage.class));
        addToNavbar(toCheckout);
        Button toOrder = new Button("Order");
        toCheckout.addClickListener(e -> getUI().orElseThrow().navigate(OrderPage.class));
        addToNavbar(toOrder);
        addToDrawer(new Button("krill"));
        Button order = new Button("order");
        order.addClickListener(event -> {
            System.out.println("sys");
            categoryCaller.create("amogus");
        });
        Button checkout = new Button("checkout");
        order.addClickListener(event -> {
            System.out.println("sys");
            categoryCaller.create("amogus");
        });
        addToDrawer(order);
        addToDrawer(checkout);
        setContent(new Text("hello world"));
    }
}
