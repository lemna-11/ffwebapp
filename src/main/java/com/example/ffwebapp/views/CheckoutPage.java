package com.example.ffwebapp.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("checkout")
public class CheckoutPage extends AppLayout {
    public CheckoutPage(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));
        layout.add(new Button("PLACEHOLDER"));

        layout.add(new Button("pay."));
        addToDrawer(layout);

    }
}
