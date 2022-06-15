package com.chatbot.service.config;

public class ChatMessage {
    private String from = "User" + Math.random();
    private String admin = "Admin";
    private String message;
  
    public ChatMessage(String from, String admin, String message) {
      this.from = from;
      this.message = message;
    }
  
    public String getFromUser() {
      return from;
    }
  
    public String getMessage() {
      return message;
    }

    public String getAdmin() {
      return admin;
    }
  }