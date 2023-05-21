package com.camigos.blog.services.impl;

import com.camigos.blog.entities.Category;
import com.camigos.blog.exceptions.ResourceNotFoundException;
import com.camigos.blog.payloads.CategoryDto;
import com.camigos.blog.repositories.CategoryRepo;
import com.camigos.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepo.save(category);

        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(
                (category) -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
