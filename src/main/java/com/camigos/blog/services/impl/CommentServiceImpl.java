package com.camigos.blog.services.impl;

import com.camigos.blog.entities.Comment;
import com.camigos.blog.entities.Post;
import com.camigos.blog.exceptions.ResourceNotFoundException;
import com.camigos.blog.payloads.CommentDto;
import com.camigos.blog.repositories.CommentRepo;
import com.camigos.blog.repositories.PostRepo;
import com.camigos.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("POST", "post id", postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);

        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
        commentRepo.delete(comment);
    }
}
