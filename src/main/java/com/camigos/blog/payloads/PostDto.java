package com.camigos.blog.payloads;

import com.camigos.blog.entities.Category;
import com.camigos.blog.entities.Comment;
import com.camigos.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    @NotEmpty
    private String title;
    private String content;
    private String imageName;
    private String addedDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comments = new HashSet<>();
}
