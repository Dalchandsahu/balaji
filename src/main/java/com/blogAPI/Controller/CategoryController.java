package com.blogAPI.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogAPI.PayLoad.ApiResponce;
import com.blogAPI.PayLoad.CategoryDto;
import com.blogAPI.Service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Create a new Category in database
	 * @since 1.0
	 * @param categoryDto
	 * @return
	 */

	@PostMapping("/CreateCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Update Category By Id
	 * @since 1.0
	 * @param categoryDto
	 * @param updateCategoryId
	 * @return
	 */

	@PutMapping("/{updateCategoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer updateCategoryId) {

		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, updateCategoryId);

		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Delete Category By Id in database
	 * @since 1.0
	 * @param CategoryId
	 * @return
	 */

	@DeleteMapping("/{CategoryId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Integer CategoryId) {
		this.categoryService.deleteCategory(CategoryId);

		return new ResponseEntity<ApiResponce>(new ApiResponce("category is delete", true), HttpStatus.OK);

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Get Category By Id
	 * @since 1.0
	 * @param categoryId
	 * @return
	 */

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {

		CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);

		return new ResponseEntity<CategoryDto>(categoryById, HttpStatus.OK);

	}

	/**
	 * @author Dalchand Sahu
	 * @apiNote To Get All Category from Database
	 * @since 1.0
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllcategory() {

		List<CategoryDto> allCategory = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategory, HttpStatus.OK);
	}

}
