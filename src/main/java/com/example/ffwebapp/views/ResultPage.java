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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Route("pickup")
public class ResultPage extends AppLayout {
    public ResultPage(OrderCaller ord){
        Order currentOrder = singleorder.getOrder();

        H1 title = new H1("Thank you for using our web store. Your order number is " + currentOrder.getId());
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "var(--lumo-space-m)");
        addToNavbar(title);
        Grid<Product> grid = new Grid<>(Product.class, false);
        grid.addColumn(Product::getName).setHeader("Name");
        grid.addColumn(product -> product.getCategory().getCategoryName()).setHeader("Category");
        grid.addColumn(product -> String.format("%d.02%d€", product.getPriceInCents() / 100, product.getPriceInCents() % 100)).setHeader("Price");
        grid.setItems(currentOrder.getProducts());

        Button exit = new Button("Exit order");
        exit.addClickListener(buttonClickEvent -> {

            UI.getCurrent().navigate("finalplace");
        });
        addToDrawer(exit);



        setPrimarySection(Section.DRAWER);
        setContent(grid);
        saveReceiptFile(currentOrder);
    }
    public boolean saveReceiptFile(Order o) {
        String filename = "Order_" + o.getId();
        int num = 1;
        File f = new File(filename);
        while (f.exists()) {
            f = new File(filename + "(" + num + ").txt");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(o.receipt());
            bw.flush();
            bw.close();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}