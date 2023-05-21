package com.camigos.blog.repositories;

import com.camigos.blog.entities.Category;
import com.camigos.blog.entities.Post;
import com.camigos.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
