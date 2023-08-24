package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.CategoryCaller;
import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.callers.ProductCaller;
import com.example.ffwebapp.middleware.entities.*;
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

    public CheckoutPage(OrderCaller orderCaller, CategoryCaller categoryCaller, ProductCaller productCaller) {
        DrawerToggle toggle = new DrawerToggle();
        Grid<Product> grid= new Grid<>(Product.class, false);


        H1 title = new H1("You have ordered:");
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

        List<Product> prods = orderCaller.readAll();
//        for(){
//            return; //this will delete the clicked on order
//
//            });
//            addToDrawer(delButton);
//        }

        addToNavbar(toggle, title);

        setPrimarySection(Section.DRAWER);

        Grid<Order> orderGrid = new Grid<>(Order.class, true);


        Button pay = new Button("pay.");
            pay.setAutofocus(true);
            pay.addClickListener(event ->{
                UI.getCurrent().navigate("pickup");
            });

        Button cancel = new Button("Cancel your order");
         cancel.setAutofocus(true);
             cancel.addClickListener(event -> {
            UI.getCurrent().navigate("");
            orderCaller.delete(singleorder.getOrder().getId());
        });
             addToDrawer(cancel);
             addToDrawer(pay);
    setContent(grid);

    }
}