package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.CategoryCaller;
import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.entities.Order;
import com.example.ffwebapp.middleware.entities.Product;
import com.example.ffwebapp.middleware.entities.ProductCategory;
import com.example.ffwebapp.middleware.entities.singleorder;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.jsoup.select.CombiningEvaluator;

import javax.swing.border.Border;
import java.util.List;

@Route("checkout")

public class CheckoutPage extends AppLayout {

    public CheckoutPage(OrderCaller orderCaller, CategoryCaller categoryCaller) {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("your order");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        List<ProductCategory> cats = categoryCaller.readAll();
        for(ProductCategory category : cats){
            addToDrawer(new Button(category.getCategoryName()));
        }

        addToNavbar(toggle, title);

        setPrimarySection(Section.DRAWER);

        Grid<Order> grid = new Grid<>(Order.class, true);


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