package com.example.application.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Comprar ingresso")
@Route(value = "comprar-ingresso", layout = MainLayout.class)
@RouteAlias(value = "comprar-ingresso", layout = MainLayout.class)
public class BuyTicketView extends HorizontalLayout {

    public BuyTicketView() {

    }
}
