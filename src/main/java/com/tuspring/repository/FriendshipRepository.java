package com.tuspring.repository;

import com.tuspring.dto.Friendship;

import java.util.List;

public interface FriendshipRepository {
    void save(Friendship friendship);
    List<Friendship> findByUserId(long userId);
    void deleteByUserId(long userId);
}
