package com.tuspring.repository;


import com.tuspring.dto.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    void saveAll(List<User> users);
    User findById(long id);
    List<User> findAll();
    void deleteById(long id);
}
