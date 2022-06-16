package com.chatbot.service;

import java.util.concurrent.ScheduledExecutorService;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

import com.chatbot.service.config.ChatMessage;
import com.chatbot.service.views.chat.ChatView;
import com.chatbot.service.views.home.Support;
import com.chatbot.service.views.home.Header;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@Route("")
@PageTitle("ChatBot")
public class MainView extends FlexLayout {
    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> messages;
    private final Chat chatSession;
    private final ScheduledExecutorService executorService;

    public MainView(UnicastProcessor<ChatMessage> publisher, Flux<ChatMessage> messages,
            Bot chatbot, ScheduledExecutorService executorService) {
        this.publisher = publisher;
        this.messages = messages;
        this.executorService = executorService;
        chatSession = new Chat(chatbot);

        getStyle().set("overflow-x", "hidden");

        add(new Header());
        add(new Support(publisher, messages));

        add(new ChatView(chatSession, executorService));
    }
}
