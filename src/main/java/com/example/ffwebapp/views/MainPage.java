package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.CategoryCaller;
import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.callers.ProductCaller;
import com.example.ffwebapp.middleware.entities.Order;
import com.example.ffwebapp.middleware.entities.OrderStatus;
import com.example.ffwebapp.middleware.entities.Product;
import com.example.ffwebapp.middleware.entities.ProductCategory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;


import java.util.List;
import java.util.Optional;


@Route("")

public class MainPage extends AppLayout {
    private Order order;

    public MainPage(CategoryCaller categoryCaller, OrderCaller ordercaller, ProductCaller productCaller) {
        DrawerToggle toggle = new DrawerToggle();
        Grid<Product> grid = new Grid<>(Product.class, false);

        H1 title = new H1("Product");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");



        List<ProductCategory> cats = categoryCaller.readAll();
        for(ProductCategory category : cats){
            Button categoryButton = new Button(category.getCategoryName());
            categoryButton.addClickListener(e -> {
                grid.setItems(productCaller.readAllByCategory(category));
            });
            addToDrawer(categoryButton);
        }


        Button checkout = new Button("Checkout");
            checkout.addClickListener(event -> {
                UI.getCurrent().navigate("checkout");
            });

        checkout.setAutofocus(true);

        addToNavbar(toggle, title);
        addToDrawer(checkout);
        setPrimarySection(Section.DRAWER);



        grid.addColumn(Product::getName).setHeader("Product Name");
        grid.addColumn(Product::getPriceInCents).setHeader("Product Price");
        grid.addColumn(Product::getCategory).setHeader("Product Category");

        grid.addSelectionListener((Product) ->{

            Optional<Product>select = Product.getFirstSelectedItem();
            if (select.isPresent()){
                if(order==null){
                    Long id = ordercaller.create(List.of(select.get()), OrderStatus.IN_PROCESS);
                    this.order = new Order();
                    order.setId(id);
                    order.setStatus(OrderStatus.IN_PROCESS);
                    order.setProducts(List.of(select.get()));
                }

            }
         }
        );

        grid.setAllRowsVisible(true);
        setContent(grid);

    }


}