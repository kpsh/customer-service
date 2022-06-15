package com.chatbot.service.views.home;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.StreamResource;

public class Header extends HorizontalLayout {
    public Header() {
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        HorizontalLayout navbar = new HorizontalLayout();
        navbar.getStyle()
                .set("background-color", "hsl(214, 33%, 13%)")
                .set("padding", "0.5em")
                .set("font-family", "Roboto")
                .set("box-shadow", "0px 0px 5px hsl(214, 33%, 50%)")
                .set("width", "100vw")
                .set("max-height", "10vh")
                .set("position", "fixed")
                .set("user-select", "none")
                .set("user-draggable", "none");

        StreamResource logoResource = new StreamResource("bot-logo.png",
                () -> getClass().getResourceAsStream("/bot-logo.png"));
        Image logo = new Image(logoResource, "My Streamed Image");
        logo.getStyle()
                .set("width", "auto")
                .set("height", "8vh");

        Tabs tabs = new Tabs();
        tabs.add(new Tab("Home"));
        tabs.add(new Tab("Customer Support"));
        tabs.add(new Tab("Login"));
        tabs.getStyle()
                .set("margin-right", "1em")
                .set("position", "absolute")
                .set("right", "0")
                .set("bottom", "0")
                .set("z-index", "1");

        tabs.getChildren().forEach(tab -> {
            tab.getElement().getStyle()
                .set("font-size", "1.3em")
                .set("color", "hsl(214, 33%, 50%)")
                .set("border-bottom", "0");
        });
        tabs.addSelectedChangeListener(event -> {
            if (tabs.getSelectedIndex() == 2) {
                //go to login route
                UI.getCurrent().navigate("login");
            } if (tabs.getSelectedIndex() == 1) {
                //go to contact route
                UI.getCurrent().navigate("support");
            }
        });

        navbar.add(logo, tabs);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FlexLayout hero = new FlexLayout();
        hero.getStyle()
                .set("user-select", "none")
                .set("user-draggable", "none");

        StreamResource heroResource = new StreamResource("suanmoo-unsplash.jpg",
                () -> getClass().getResourceAsStream("/suanmoo-unsplash.jpg"));
        Image heroImg = new Image(heroResource, "My Streamed Image");
        heroImg.setWidth(100, Unit.VW);
        heroImg.setHeight("auto");
        heroImg.getStyle().set("object-fit", "cover")
                .set("width", "100vw")
                .set("height", "100vh")
                .set("filter", "brightness(0.3) blur(2px)")
                .set("z-index", "-1");

        H1 heroTitle = new H1("Welcome to the ChatBot");
        heroTitle.getStyle()
                .set("position", "absolute")
                .set("left", "0")
                .set("bottom", "0")
                .set("padding", "0 0 5% 5%")
                .set("color", "#f1f1f1");

        Paragraph heroDescription = new Paragraph(
                "A Spring Boot application allowing users to get solutions fast. This is a demo application.");
        heroDescription.getStyle()
                .set("position", "absolute")
                .set("left", "0")
                .set("bottom", "0")
                .set("padding", "0 0 3% 5%")
                .set("color", "#a1a1a1");

        hero.add(heroImg, heroTitle, heroDescription);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        add(navbar, hero);
    }
}
