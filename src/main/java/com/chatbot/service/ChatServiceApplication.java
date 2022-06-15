package com.chatbot.service;

import com.chatbot.service.config.ChatMessage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.alicebot.ab.Bot;
import org.alicebot.ab.configuration.BotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@Push
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class ChatServiceApplication extends SpringBootServletInitializer implements AppShellConfigurator {
	public static void main(String[] args) {
		SpringApplication.run(ChatServiceApplication.class, args);
	}

	@Bean
	UnicastProcessor<ChatMessage> publisher() {
		return UnicastProcessor.create();
	}

	@Bean
	Flux<ChatMessage> messages(UnicastProcessor<ChatMessage> publisher) {
		return publisher.replay(30).autoConnect();
	}

	@Bean
	public Bot chatbot() {
		return new Bot(BotConfiguration.builder()
				.name("chatbot")
				.path("src/main/resources")
				.build());
	}

	@Bean
	public ScheduledExecutorService executorService() {
		return Executors.newScheduledThreadPool(2);
	}
}
