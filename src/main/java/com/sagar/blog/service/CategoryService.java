package com.sagar.blog.service;

import java.util.List;

import com.sagar.blog.dto.CategoryDto;

public interface CategoryService {
	CategoryDto addCategory(CategoryDto categoryDto);
	CategoryDto getCategory(Long categoryId);
	List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    void deleteCategory(Long categoryId);
}
