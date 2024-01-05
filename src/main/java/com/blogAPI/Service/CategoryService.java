package com.blogAPI.Service;

import java.util.List;

import com.blogAPI.PayLoad.CategoryDto;

public interface CategoryService {

	// Create Category
	CategoryDto createCategory(CategoryDto categoryDto);

	// Update Category
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete Category
	void deleteCategory(Integer categoryId);

	// GetCategoryById
	CategoryDto getCategoryById(Integer categoryId);

	// Get All Category
	List<CategoryDto> getAllCategory();

}
