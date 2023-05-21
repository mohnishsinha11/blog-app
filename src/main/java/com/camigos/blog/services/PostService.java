package com.camigos.blog.services;


import com.camigos.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all Posts
    List<PostDto> getAllPost();

    //get single Post
    PostDto getPostById();

    //get all Posts by Category
    List<PostDto> getAllPostsByCategoryId(Integer categoryId);

    //get all Posts By User
    List<PostDto> getAllPostsByUserId(Integer userId);

    //search post
    List<PostDto> searchPosts(String keyword);
}
