package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.entities.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("checkout")
public class CheckoutPage extends AppLayout {

    public CheckoutPage(OrderCaller orderCaller) {
        DrawerToggle toggle = new DrawerToggle();
        Grid<Product> grid = new Grid<>(Product.class, false);
        grid.addColumn(Product::getName).setHeader("Name");
        grid.addColumn(product -> product.getCategory().getCategoryName()).setHeader("Category");
        grid.addColumn(product -> String.format("%d.02%d€", product.getPriceInCents() / 100, product.getPriceInCents() % 100)).setHeader("Price");
        Order currentOrder = singleorder.getOrder();
        grid.setItems(currentOrder.getProducts());

        H1 title = new H1("You have ordered:");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");
        addToNavbar(toggle, title);

        setPrimarySection(Section.DRAWER);

        Button pay = new Button("Pay your order");
        pay.setAutofocus(true);
        pay.addClickListener(event -> UI.getCurrent().navigate("pickup"));

        Button cancel = new Button("Cancel your order");
        cancel.setAutofocus(true);
        cancel.addClickListener(event -> {
            orderCaller.update(currentOrder.getId(), currentOrder.getProducts(), OrderStatus.CANCELLED);
            singleorder.setOrder(null);
            UI.getCurrent().navigate("");
        });
        addToDrawer(cancel);
        addToDrawer(pay);
        setContent(grid);
    }
}