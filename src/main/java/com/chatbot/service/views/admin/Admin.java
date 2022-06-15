package com.chatbot.service.views.admin;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("admin")
@PageTitle("Dashboard")
@CssImport(value = "./css/style.css", themeFor = "vaadin-app-layout")
public class Admin extends AppLayout {
	public Admin() {
		DrawerToggle toggle = new DrawerToggle();

		Tabs tabs = new Tabs();
		tabs.add(new Tab("Dashboard"));
		tabs.add(new Tab("Active Chats"));
		tabs.add(new Tab("Archive"));
		tabs.add(new Tab("Bot Settings"));
		tabs.add(new Tab("Banned IPs"));
		tabs.setOrientation(Tabs.Orientation.VERTICAL);

		H4 title = new H4(tabs.getSelectedTab().getLabel());

		Button logout = new Button("Logout");
		logout.addClassName("logout");
		logout.addClickListener(event -> {
			getUI().ifPresent(ui -> ui.navigate(""));
		});

		showDashboard();
		tabs.addSelectedChangeListener(event -> {
			title.setText(tabs.getSelectedTab().getLabel());
			if (tabs.getSelectedTab().getLabel().equals("Dashboard")) {
				showDashboard();
			} else if (tabs.getSelectedTab().getLabel().equals("Active Chats")) {
				setContent(ChatSettings.ActiveChats());
			} else if (tabs.getSelectedTab().getLabel().equals("Archive")) {
				setContent(ChatSettings.Archive());
			} else if (tabs.getSelectedTab().getLabel().equals("Bot Settings")) {
				setContent(BotSettings.Bot());
			} else if (tabs.getSelectedTab().getLabel().equals("Banned IPs")) {
				setContent(BotSettings.BannedIPs());
			}
		});
		addToDrawer(tabs);
		addToNavbar(toggle, title, logout);
	}

	public void showDashboard() {
		Div div = new Div();
		div.getStyle().set("margin", "1em 1.5em");

		H4 title = new H4("Dashboard");
		div.add(title);

		Span name = new Span("part1");
		VerticalLayout content = new VerticalLayout(name);
		content.setSpacing(false);
		content.setPadding(false);
		Details details = new Details("Detail 1", content);
		details.addClassName("details");
		details.setOpened(true);

		Span name2 = new Span("part2");
		VerticalLayout content2 = new VerticalLayout(name2);
		content2.setSpacing(false);
		content2.setPadding(false);
		Details details2 = new Details("Detail 2", content2);
		details2.addClassName("details");
		details2.setOpened(true);

		Span name3 = new Span("part3");
		VerticalLayout content3 = new VerticalLayout(name3);
		content3.setSpacing(false);
		content3.setPadding(false);
		Details details3 = new Details("Detail 3", content3);
		details3.addClassName("details");
		details3.setOpened(true);

		div.add(details, details2, details3);
		setContent(div);
	}
}