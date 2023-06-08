package com.example.ffwebapp.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("")
public class MainPage extends AppLayout {
    public MainPage(){
        addToNavbar(new H1("why"));
        addToDrawer(new Button("krill"));
        setContent(new Text("hello world"));
    }
}
