package com.blog_app.controllers;

import java.util.List;

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

import com.blog_app.payloads.CategoryDto;
import com.blog_app.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
@PostMapping("/category")	
public ResponseEntity<CategoryDto> crateCategory(@Valid  @RequestBody CategoryDto categoryDto)
{
	
	CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
	
	return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
	
}
	
@PutMapping("/category/{id}")	
public ResponseEntity<CategoryDto> updateCategory(@Valid  @RequestBody  CategoryDto categoryDto,@PathVariable("id") Integer id)
{
	
	
	CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, id);
	
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateCategory);
	
	
}
@GetMapping("/category/{id}")	
public ResponseEntity<CategoryDto> getCategory(@PathVariable("id")  Integer id)
{
		CategoryDto categoryById = this.categoryService.getCategoryById(id);
	
	return ResponseEntity.status(HttpStatus.OK).body(categoryById);
}
	

@GetMapping("/category")
public ResponseEntity<List<CategoryDto>> getAllCategories()
{
	List<CategoryDto> allCategories = this.categoryService.getallCategories();
	
	return ResponseEntity.status(HttpStatus.OK).body(allCategories);
}


@DeleteMapping("/category/{id}")
public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id)
{
	this.categoryService.deleteCategory(id);
	 return ResponseEntity.ok("category deleted");	
}
	
	
	
	
}
