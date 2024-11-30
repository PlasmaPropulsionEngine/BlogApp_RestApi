package com.blog_app.services;

import java.util.List;

import com.blog_app.payloads.CategoryDto;

public interface CategoryService {

	
	//create 
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	
	//delete
	
	public void deleteCategory(Integer id);
	
	//get  single category
	
	public CategoryDto getCategoryById(Integer id);
	
	//get all 
	
	public List<CategoryDto> getallCategories();
	
	
	
	
	
	
}
