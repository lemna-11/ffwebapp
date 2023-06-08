package com.example.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainPage extends AppLayout {
    public MainPage(){
        Text text = new Text("hello world");
        setContent(text);
    }
}
