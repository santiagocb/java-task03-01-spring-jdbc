package com.tuspring;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DataGenerator {

    private static final int NUM_USERS = 1000;
    private static final int NUM_FRIENDSHIPS = 70000;
    private static final int NUM_POSTS = 1000;
    private static final int NUM_LIKES = 300000;

    private final Connection connection;

    public DataGenerator(Connection conn) {
        connection = conn;
    }

    public void generateUsers() throws SQLException {
        Random random = new Random();
        String[] names = {"John", "Jane", "Michael", "Sarah", "Robert", "Emily", "David", "Laura", "Daniel", "Sophia"};
        String[] surnames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

        String sql = "INSERT INTO Users (name, surname, birthdate) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < NUM_USERS; i++) {
                String name = names[random.nextInt(names.length)];
                String surname = surnames[random.nextInt(surnames.length)];
                String birthdate = getRandomBirthdate(random);

                ps.setString(1, name);
                ps.setString(2, surname);
                ps.setString(3, birthdate);
                ps.addBatch();

                if (i % 1000 == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
        }
    }

    private String getRandomBirthdate(Random random) {
        int year = 1970 + random.nextInt(31);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);
        return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
    }

    public void generateFriendships() throws SQLException {
        Random random = new Random();
        String sql = "INSERT INTO Friendships (userid1, userid2, timestamp) VALUES (?, ?, ?)";

        // Set to track unique (userId1, userId2) pairs
        Set<String> friendshipSet = new HashSet<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int friendshipCount = 0;
            while (friendshipCount < NUM_FRIENDSHIPS) {
                int userId1 = random.nextInt(NUM_USERS) + 1;
                int userId2;

                // Ensure no self-friendship and that the pair is unique
                do {
                    userId2 = random.nextInt(NUM_USERS) + 1;
                } while (userId1 == userId2 || friendshipSet.contains(userId1 + "-" + userId2) || friendshipSet.contains(userId2 + "-" + userId1));

                // Add friendship to the set
                friendshipSet.add(userId1 + "-" + userId2);

                // Insert the friendship into the database
                ps.setInt(1, userId1);
                ps.setInt(2, userId2);
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps.addBatch();

                friendshipCount++;

                if (friendshipCount % 1000 == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();  // Insert remaining batch
        }
    }

    public void generatePosts() throws SQLException {
        Random random = new Random();
        String sql = "INSERT INTO Posts (userId, text, timestamp) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < NUM_POSTS; i++) {
                int userId = random.nextInt(NUM_USERS) + 1;
                String postText = "This is post #" + (i + 1) + " by user " + userId;

                ps.setInt(1, userId);
                ps.setString(2, postText);
                ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.addBatch();

                if (i % 1000 == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
        }
    }

    public void generateLikes() throws SQLException {
        Random random = new Random();
        String sql = "INSERT INTO Likes (postId, userId, timestamp) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < NUM_LIKES; i++) {
                int postId = random.nextInt(NUM_POSTS) + 1;
                int userId = random.nextInt(NUM_USERS) + 1;

                ps.setInt(1, postId);
                ps.setInt(2, userId);
                ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.addBatch();

                if (i % 1000 == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
        }
    }
}
