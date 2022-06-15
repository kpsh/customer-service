package com.chatbot.service.views.admin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;

@CssImport("./css/style.css")
public class ChatSettings {

    public static Component ActiveChats() {
        Div div = new Div();
        div.getStyle().set("margin", "1em 1.5em");

        H4 title = new H4("Active Chats");
        div.add(title);

        Select<String> select = new Select<>();
        select.setLabel("Select Chat");
        select.setItems("Chat 1", "Chat 2", "Chat 3");
        div.add(select);

        MessageList messageList = new MessageList();
        messageList.setHeight("30vh");
        messageList.addClassName("operator");
        MessageInput messageInput = new MessageInput();

        messageInput.addSubmitListener(listener -> {
            MessageListItem newMessage = new MessageListItem(
                    listener.getValue(), Instant.now(), "Operator");
            newMessage.setUserColorIndex(5);
            List<MessageListItem> items = new ArrayList<>(messageList.getItems());
            items.add(newMessage);
            messageList.setItems(items);
        });
        div.add(messageList, messageInput);

        return div;
    }

    public static Component Archive() {
        Div div = new Div();
        div.getStyle().set("margin", "1em 1.5em");

        H4 title = new H4("Archive");
        div.add(title);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("100%");
        horizontalLayout.setHeight("60vh");

        Div archiveDiv = new Div();
        archiveDiv.getStyle().set("min-width", "40vh");
        archiveDiv.getStyle().set("height", "100%");
        archiveDiv.addClassName("operator");

        Div archiveDiv1 = new Div();
        archiveDiv1.getStyle().set("min-width", "40vh");
        archiveDiv1.getStyle().set("height", "100%");
        archiveDiv1.addClassName("operator");
        
        Div archiveDiv2 = new Div();
        archiveDiv2.getStyle().set("min-width", "40vh");
        archiveDiv2.getStyle().set("height", "100%");
        archiveDiv2.addClassName("operator");

        horizontalLayout.add(archiveDiv, archiveDiv1, archiveDiv2);

        Button button = new Button("Delete");
        button.getStyle().set("margin-top", "3em");
        button.addClickListener(e -> {
            horizontalLayout.remove(archiveDiv);
        });

        div.add(horizontalLayout, button);

        return div;
    }
}