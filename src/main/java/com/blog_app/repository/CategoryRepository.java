package com.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_app.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
	
}
