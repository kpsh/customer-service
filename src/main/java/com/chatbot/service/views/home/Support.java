package com.chatbot.service.views.home;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.chatbot.service.config.ChatMessage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@Route("support")
public class Support extends VerticalLayout {
    // user name with 3 random numbers
    private Integer userId = (int) (Math.random() * 1000);
    private Integer userColor = (int) (Math.random() * 7);

    public Support(UnicastProcessor<ChatMessage> publisher, Flux<ChatMessage> messages) {
        this.setSizeFull();

        HorizontalLayout header = new HorizontalLayout();
        header.setWidth("100%");
        header.setHeight("10vh");

        H3 title = new H3("Customer Support");

        Button goBack = new Button("Go Back");
        goBack.addClickListener(listener -> getUI().ifPresent(ui -> ui.navigate("")));

        header.add(title, goBack);

        MessageList messageList = new MessageList();
        messageList.setWidth("100%");
        messageList.setHeight("70%");

        MessageInput messageInput = new MessageInput();
        messageInput.setWidth("100%");
        messageInput.setHeight("10%");

        List<MessageListItem> items = new ArrayList<>(messageList.getItems());
        MessageListItem hello = new MessageListItem(
                "Welcome!\nPlease describe your problem below.", Instant.now(), "Information");
        items.add(hello);
        messageList.setItems(items);

        messageInput.addSubmitListener(submitEvent -> {
            publisher.onNext(new ChatMessage("User" + userId, null, submitEvent.getValue()));
        });

        messages.subscribe(message -> {
            MessageListItem send = new MessageListItem(message.getMessage(), Instant.now(), message.getFromUser());
            send.setUserColorIndex(userColor);
            items.add(send);
            messageList.setItems(items);
        });
        add(header, messageList, messageInput);
    }
}