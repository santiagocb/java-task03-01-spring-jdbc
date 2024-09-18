package com.tuspring;

import com.tuspring.dto.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class DataInserter {

    private final JdbcTemplate jdbcTemplate;

    public DataInserter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUsers(List<User> users) {
        String sql = "INSERT INTO Users (name, surname, birthdate) VALUES (?, ?, ?)";

        for (User user : users) {
            jdbcTemplate.update(sql, user.getName(), user.getSurname(), Date.valueOf(user.getBirthdate()));
        }
    }

    // Similarly, you can write methods to insert friendships, posts, and likes.
}
