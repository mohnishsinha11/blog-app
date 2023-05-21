package com.camigos.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private String categoryId;

    @NotBlank
    @Size(min = 4, message = "Category Title must be of minimum 4 characters")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Category Description must be of minimum 10 characters")
    private String categoryDescription;
}
