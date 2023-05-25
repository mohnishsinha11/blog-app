package com.camigos.blog.controllers;

import com.camigos.blog.payloads.ApiResponse;
import com.camigos.blog.payloads.PostDto;
import com.camigos.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/posts/post/{postId}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/posts/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable Integer categoryId){
        return new ResponseEntity<>(postService.getAllPostsByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("posts/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getAllPostsByUserId(userId), HttpStatus.OK);
    }
}
