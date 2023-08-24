package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.CategoryCaller;
import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.callers.ProductCaller;
import com.example.ffwebapp.middleware.entities.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.util.List;

@Route("order")
public class OrderPage extends AppLayout {
    public OrderPage(OrderCaller orderCaller, CategoryCaller categoryCaller, ProductCaller productCaller) {
        DrawerToggle toggle = new DrawerToggle();
        Grid<Product> gridd = new Grid<>(Product.class, false);


        H1 title = new H1("your order");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        List<ProductCategory> cats = categoryCaller.readAll();
        for(ProductCategory category : cats){
            Button categoryButton = new Button(category.getCategoryName());
            addToDrawer(new Button(category.getCategoryName()));
            categoryButton.addClickListener(e -> {
                UI.getCurrent().navigate("");
                gridd.setItems(productCaller.readAllByCategory(category));
            });
        }

        addToNavbar(toggle, title);

        setPrimarySection(Section.DRAWER);

        Grid<Order> grid = new Grid<>(Order.class, true);

        Button pay = new Button("Pay your order");
        pay.setAutofocus(true);
        pay.addClickListener(event -> {
            UI.getCurrent().navigate("checkout");
        });

        Button cancel = new Button("Cancel your order");
        cancel.setAutofocus(true);
        cancel.addClickListener(event -> {
            UI.getCurrent().navigate("");
            orderCaller.delete(singleorder.getOrder().getId());
        });

        addToDrawer(cancel);
        setContent(grid);

    }
}

