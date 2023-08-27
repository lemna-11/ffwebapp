package com.example.ffwebapp.views;

import com.example.ffwebapp.middleware.callers.OrderCaller;
import com.example.ffwebapp.middleware.entities.Order;
import com.example.ffwebapp.middleware.entities.OrderStatus;
import com.example.ffwebapp.middleware.entities.Product;
import com.example.ffwebapp.middleware.entities.singleorder;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Route("finalplace")
public class TakeoutPage extends AppLayout {

        public TakeoutPage(OrderCaller orderCaller) {
            List<Order> allOrders = orderCaller.readAllOrders();
            DrawerToggle toggle = new DrawerToggle();


            Grid<Order> grid = new Grid<>(Order.class, false);
            grid.addColumn(Order::getId).setHeader("ID");
            grid.addColumn(Order::getStatus).setHeader("Status");
            grid.addColumn(order ->{
                Button finishbuton =  new Button();
                finishbuton.addClickListener(buttonClickEvent -> {
                    order.setStatus(OrderStatus.COMPLETED);

                });
                return finishbuton;
            });
            addToNavbar(toggle);
            allOrders.stream().filter(order -> order.getStatus() != OrderStatus.CANCELLED && order.getStatus() != OrderStatus.COMPLETED).collect(Collectors.toList());

            setPrimarySection(Section.DRAWER);


            setContent(grid);
        }
    }

