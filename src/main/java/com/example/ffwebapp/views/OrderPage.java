package com.example.ffwebapp.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("order")
public class OrderPage extends AppLayout {
    public OrderPage() {
        addToNavbar(new H2("you have ordered:"));
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new Button("placeholder for order"));
        layout.add(new Button("placeholder for order"));
        layout.add(new Button("placeholder for order"));
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setItems("sauce");
        checkboxGroup.setItems("sauce");
        checkboxGroup.setItems("sauce");
        checkboxGroup.setItems("sauce");
        checkboxGroup.setItems("sauce");
        layout.add(checkboxGroup);
        addToDrawer(layout);
    }
}
