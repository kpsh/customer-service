package com.chatbot.service.views.admin;

import com.fasterxml.jackson.databind.JsonNode;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.textfield.TextField;

public class BotSettings {
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Component Bot() {
		// div
		Div div = new Div();
		div.getStyle().set("margin", "1em 1.5em");

		H4 title = new H4("Bot Settings");
		div.add(title);

		final Grid<JsonNode> grid = new Grid<JsonNode>();
		grid.addColumn(node -> node.get("id")).setHeader("Id");
		grid.addColumn(node -> node.get("answer")).setHeader("Answer");
		grid.addColumn(node -> node.get("key1")).setHeader("Key1");
		grid.addColumn(node -> node.get("key2")).setHeader("Key2");
		grid.addColumn(node -> node.get("key3")).setHeader("Key3");
		grid.addColumn(node -> node.get("key4")).setHeader("Key4");

		final Button refresh = new Button("refresh", e -> grid.setItems());

		div.add(grid, refresh);

		FormLayout form = new FormLayout();
		form.setWidth("100%");
		form.setResponsiveSteps(
				new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
				new FormLayout.ResponsiveStep("350px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

		TextField answer = new TextField("answer");
		TextField key1 = new TextField("key1");
		TextField key2 = new TextField("key2");
		TextField key3 = new TextField("key3");
		TextField key4 = new TextField("key4");
		Button add = new Button("add", e -> {
			// todo
		});

		form.add(answer, key1, key2, key3, key4, add);
		div.add(form);
		return div;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Component BannedIPs() {
		// div
		Div div = new Div();
		div.getStyle().set("margin", "1em 1.5em");

		H4 title = new H4("Banned IPs");
		div.add(title);

		MultiSelectListBox<String> listBox = new MultiSelectListBox<>();
		listBox.setItems("192.168.0.1");

		TextField ip = new TextField();
		ip.setPlaceholder("add IP to ban");
		Button add = new Button("add", e -> {
			// todo
		});

		div.add(listBox, ip, add);

		return div;
	}
}