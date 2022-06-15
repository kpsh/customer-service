package com.chatbot.service.views.chat;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import org.alicebot.ab.Chat;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.server.StreamResource;

@CssImport("./css/style.css")
public class ChatView extends FlexLayout {
	public ChatView(Chat chatSession, ScheduledExecutorService executorService, UI ui) {
		
		getStyle().set("overflow-x", "hidden");

		Div div = new Div();
		div.addClassName("chat-div");

		StreamResource logoResource = new StreamResource("bot-logo.png",
				() -> getClass().getResourceAsStream("/bot-logo.png"));
		Image logo = new Image(logoResource, "chat");
		logo.getStyle().set("width", "auto").set("height", "3em");

		Button closeBtn = new Button(new Icon("lumo", "cross"));
		closeBtn.addClassName("close-btn");

		MessageList messageList = new MessageList();
		messageList.addClassName("message-list");

		MessageInput messageInput = new MessageInput();
		messageInput.addClassName("message-input");

		div.add(logo, closeBtn, messageList, messageInput);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		logo.addClickListener(e -> {
			div.getStyle()
					.set("width", "35%")
					.set("height", "70%")
					.set("padding", "0")
					.set("border-radius", "2%");
			logo.getStyle()
					.set("width", "auto")
					.set("height", "4vh")
					.set("margin", "0.5em");
			closeBtn.getStyle().set("visibility", "visible");
			messageList.getStyle().set("visibility", "visible");
			messageInput.getStyle().set("visibility", "visible");
		});
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		closeBtn.addClickListener(e -> {
			div.getStyle()
					.set("width", "3em")
					.set("height", "3em")
					.set("padding", "0.5em")
					.set("border-radius", "30%");
			logo.getStyle()
					.set("width", "auto")
					.set("height", "3em")
					.set("margin", "0");
			closeBtn.getStyle().set("visibility", "hidden");
			messageList.getStyle().set("visibility", "hidden");
			messageInput.getStyle().set("visibility", "hidden");
		});
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		List<MessageListItem> items = new ArrayList<>(messageList.getItems());
		MessageListItem hello = new MessageListItem(
				"Welcome!\nPlease describe your problem below.", Instant.now(), "ChatBot");
		items.add(hello);
		messageList.setItems(items);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		messageInput.addSubmitListener(submitEvent -> {
			MessageListItem message = new MessageListItem(submitEvent.getValue(), Instant.now(), "You");
			items.add(message);
			message.setUserColorIndex(2);
			messageList.setItems(items);

			String answer = chatSession.multisentenceRespond(submitEvent.getValue());
			MessageListItem aiAnswer = new MessageListItem(answer, Instant.now(), "ChatBot");
			items.add(aiAnswer);
			messageList.setItems(items);
			System.out.println(answer);
		});
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		add(div);
	}
}