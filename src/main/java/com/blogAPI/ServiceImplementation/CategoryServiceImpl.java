package com.blogAPI.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogAPI.Entity.Category;
import com.blogAPI.Exception.ResourceNotFoundException;
import com.blogAPI.PayLoad.CategoryDto;
import com.blogAPI.Repository.CategoryRepository;
import com.blogAPI.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepository.save(category);
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "CategoryId", categoryId));
		category.setCategoryTitel(categoryDto.getCategoryTitel());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCategory = this.categoryRepository.save(category);

		return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Categotry", "CategoryId", categoryId));
		this.categoryRepository.delete(category);

	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> findAll = this.categoryRepository.findAll();

		List<CategoryDto> CategoryDtos = findAll.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return CategoryDtos;
	}

}
