package com.blog_app.services;

import java.util.List;

import com.blog_app.models.Category;
import com.blog_app.models.Post;
import com.blog_app.payloads.PostDto;
import com.blog_app.payloads.PostResponse;

public interface PostService {

//create	
public PostDto createPost(PostDto postDto,Integer CategoryId,Integer userId);
	
//update	
public PostDto updatePost(PostDto postDto ,Integer id);	
	
//delete	
public void deletePost(Integer id);

//get all posts
public PostResponse getAllPost(Integer pageNumber ,Integer pageSize,String sortBy);

//get single post by id
public PostDto getPostByid(Integer id);

//get post by category
	
public List<PostDto>getPostByCategory(Integer catId);

//get posts by user

public List<PostDto> getPostByUser(Integer Userid);

//search posts
public List<PostDto>searchPosts(String keyword);

}
