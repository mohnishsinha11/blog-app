package com.camigos.blog.services.impl;

import com.camigos.blog.entities.Category;
import com.camigos.blog.entities.Post;
import com.camigos.blog.entities.User;
import com.camigos.blog.exceptions.ResourceNotFoundException;
import com.camigos.blog.payloads.PostDto;
import com.camigos.blog.repositories.CategoryRepo;
import com.camigos.blog.repositories.PostRepo;
import com.camigos.blog.repositories.UserRepo;
import com.camigos.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepo.save(post);

        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public PostDto getPostById() {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
