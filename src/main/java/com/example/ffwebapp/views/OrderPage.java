package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.entities.OrderStatus;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("order")
public class OrderPage extends AppLayout {
    public OrderPage(OrderCaller orderCaller) {
        addToNavbar(new H2("you have ordered:"));
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        // EXAMPLE BUTTON
        Button order = new Button("order");
        order.addClickListener(event -> orderCaller.create(List.of(), OrderStatus.IN_PROCESS));
        layout.add(order);
        // EXAMPLE BUTTON
        layout.add(new Button("placeholder for order"));
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setItems("sauce", "ketchup", "mayonnaise");
        layout.add(checkboxGroup);
        addToDrawer(layout);
    }
}
