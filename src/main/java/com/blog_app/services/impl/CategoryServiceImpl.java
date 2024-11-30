package com.blog_app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.models.Category;
import com.blog_app.models.User;
import com.blog_app.payloads.CategoryDto;
import com.blog_app.payloads.UserDto;
import com.blog_app.repository.CategoryRepository;
import com.blog_app.services.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//create 	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	
		
		Category category = this.modelMapper.map(categoryDto,Category.class);
		
		Category savecat = this.categoryRepository.save(category);
		
		
		return this.modelMapper.map(savecat, CategoryDto.class);
		
	}
//update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
	
		
		Category  category = this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category id ", id));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category save = this.categoryRepository.save(category);
		
		CategoryDto categoryDto2 = this.modelMapper.map(save,CategoryDto.class);
		
		return categoryDto2;
	}

	@Override
	public void deleteCategory(Integer id) {
	
		Category category = this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category id", id));
		
		this.categoryRepository.delete(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {

		Category category = this.categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category id",id));
		
		CategoryDto map = this.modelMapper.map(category,CategoryDto.class);
		return map;
	}

	@Override
	public List<CategoryDto> getallCategories() {
		
		List<Category> allCategories = this.categoryRepository.findAll();
		
		List<CategoryDto> collect = allCategories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		return collect;
		
	}
	
	

	
	
	
	
	
	
	
	
	
}
