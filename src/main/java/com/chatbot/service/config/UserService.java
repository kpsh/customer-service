package com.chatbot.service.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT id, username, password FROM login",
                (rs, rowNum) -> new User(rs.getInt("id"),
                        rs.getString("username"), rs.getString("password")));
    }

    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE customers SET first_name=?, last_name=? WHERE id=?",
                user.getUsername(), user.getPassword(), user.getId());
    }
}
