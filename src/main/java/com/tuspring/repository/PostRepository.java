package com.tuspring.repository;


import com.tuspring.dto.Post;

import java.util.List;

public interface PostRepository {
    void save(Post post);
    List<Post> findByUserId(long userId);
    void deleteById(long id);
}
