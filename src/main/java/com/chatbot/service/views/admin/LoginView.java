package com.chatbot.service.views.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.chatbot.service.config.User;
import com.chatbot.service.config.UserService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login")
public class LoginView extends Composite<LoginOverlay> {

    @Autowired
    private UserService userService;

    private User user;

    public LoginView() {
        LoginOverlay loginOverlay = getContent();
        loginOverlay.setTitle("Login");
        loginOverlay.setDescription("Please login to continue");
        loginOverlay.setForgotPasswordButtonVisible(false);
        loginOverlay.setOpened(true);

        loginOverlay.addLoginListener(event -> {
            user = userService.findAll().stream()
                    .filter(u -> u.getUsername().equals(event.getUsername())
                            && u.getPassword().equals(event.getPassword()))
                    .findFirst().orElse(null);
            if (user != null) {
                loginOverlay.setOpened(false);
                Notification success = new Notification("Login successful", 3000, Position.BOTTOM_STRETCH);
                success.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                success.open();
                UI.getCurrent().navigate("admin");
            } else {
                Notification error = new Notification("Login failed", 3000, Position.BOTTOM_STRETCH);
                error.addThemeVariants(NotificationVariant.LUMO_ERROR);
                error.open();
                UI.getCurrent().getPage().executeJs("setTimeout(function() {window.location.reload(true);}, 1500);");
            }
        });
    }
}