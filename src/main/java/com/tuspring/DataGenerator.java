package com.tuspring;

import com.tuspring.dto.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= count; i++) {
            users.add(new User("Name" + i, "Surname" + i, "1990-01-01"));
        }

        return users;
    }
}
