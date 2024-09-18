package com.tuspring.repository;


import com.tuspring.dto.Like;

import java.util.List;

public interface LikeRepository {
    void save(Like like);
    List<Like> findByPostId(long postId);
    void deleteByPostId(long postId);
}
