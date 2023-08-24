package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.entities.Order;
import com.example.ffwebapp.middleware.entities.OrderStatus;
import com.example.ffwebapp.middleware.entities.Product;
import com.example.ffwebapp.middleware.entities.singleorder;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("pickup")
public class ResultPage extends AppLayout {
    public ResultPage(OrderCaller ord){
        H1 title = new H1("Thank you for using our web store");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "var(--lumo-space-m)");
        addToNavbar(title);
        Order currentOrder = singleorder.getOrder();
        Grid<Product> grid = new Grid<>(Product.class, false);
        grid.addColumn(Product::getName).setHeader("Name");
        grid.addColumn(product -> product.getCategory().getCategoryName()).setHeader("Category");
        grid.addColumn(product -> String.format("%d.02%d€", product.getPriceInCents() / 100, product.getPriceInCents() % 100)).setHeader("Price");
        grid.setItems(currentOrder.getProducts());

        Button exit = new Button("Exit order");
        exit.addClickListener(buttonClickEvent -> {
            currentOrder.setStatus(OrderStatus.COMPLETED);
            ord.update(currentOrder.getId(), currentOrder.getProducts(), currentOrder.getStatus());
            singleorder.setOrder(null);
            UI.getCurrent().navigate("");
        });
        addToDrawer(exit);

        setPrimarySection(Section.DRAWER);
        setContent(grid);
    }
}