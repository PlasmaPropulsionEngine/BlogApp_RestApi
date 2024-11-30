package com.blog_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog_app.models.Category;
import com.blog_app.models.Post;
import com.blog_app.models.User;
import com.blog_app.payloads.PostDto;

public interface PostRepository extends JpaRepository<Post,Integer> {

	
	
	public List<Post>findByUser(User user);
	
	public List<Post> findByCategory(Category category);
	
	public List<Post>findByTitleContaining(String title);
	
//	@Query("select p from Post p where p.title like :value")
//	public List<Post>findByTitleContaining(@param("value")  String title);

	
}
