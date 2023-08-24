package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.entities.Order;
import com.example.ffwebapp.middleware.entities.OrderStatus;
import com.example.ffwebapp.middleware.entities.Product;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Optional;

import static com.example.ffwebapp.middleware.entities.singleorder.order;
@Route("pickup")
public class PickupPage extends AppLayout {
    public PickupPage(OrderCaller ord){
        H1 title = new H1("PICKUP ORDERS");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "var(--lumo-space-m)");
        addToNavbar(title);

        Grid<Order> grid = new Grid<>(Order.class, true);
        //on click, set the order, dunno how

        setPrimarySection(Section.DRAWER);

        Long orderselect = order.getId();
                order.setStatus(OrderStatus.COMPLETED);
    }

}