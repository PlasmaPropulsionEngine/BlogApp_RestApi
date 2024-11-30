package com.blog_app.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.models.Category;
import com.blog_app.models.Post;
import com.blog_app.models.User;
import com.blog_app.payloads.CategoryDto;
import com.blog_app.payloads.PostDto;
import com.blog_app.payloads.PostResponse;
import com.blog_app.repository.CategoryRepository;
import com.blog_app.repository.PostRepository;
import com.blog_app.repository.UserRepository;
import com.blog_app.services.PostService;

@Service
public class PostSetviceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
//create post	
	@Override
	public PostDto createPost(PostDto postDto,Integer CategoryId , Integer UserId) {
			
	User user=this.userRepository.findById(UserId).orElseThrow(()->new ResourceNotFoundException("user", UserId));
	
	Category category= this.categoryRepository.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("category", CategoryId));
			
			Post post = this.modelMapper.map(postDto,Post.class);
			
			post.setImagename("default.png");
			post.setAddedDate(new Date());
			post.setCategory(category);
			post.setUser(user);
			
			Post save = this.postRepository.save(post);	
		return this.modelMapper.map(save,PostDto.class);
	}

//update posts	
	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {

		Post post = this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post ",id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImagename(postDto.getImagename());
		Post save = this.postRepository.save(post);
		
		PostDto postDto2 = this.modelMapper.map(save,PostDto.class);
		return postDto2;
	}

	
//delete post 	
	
	@Override
	public void deletePost(Integer id) {
	
		Post post = this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post ID",id));
	
		this.postRepository.delete(post);
		
	}

	
//get all posts	
	@Override
	public PostResponse getAllPost(Integer pageNumber ,Integer pageSize,String sortBy) {

		Pageable p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
		 Page<Post> pagePost = this.postRepository.findAll(p);
		 List<Post> content = pagePost.getContent();
		List<PostDto> collect = content.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(collect);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
	
		return postResponse;
	}

//get post by  post Id	
	@Override
	public PostDto getPostByid(Integer id) {
	
		Post post = this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post id",id));
		
		PostDto postDto = this.modelMapper.map(post,PostDto.class);
		
		return postDto;
	}

	
//get post by category ID	
	@Override
	public List<PostDto> getPostByCategory(Integer catId) {
		
		Category category = this.categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("category id",catId));
		
		
		List<Post> posts = this.postRepository.findByCategory(category);
		
		List<PostDto> collect = posts.stream().map((p)->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
		
		
		return  collect;
	}

//get posts by User ID	
	@Override
	public List<PostDto> getPostByUser(Integer Userid) {

		User user = this.userRepository.findById(Userid).orElseThrow(()->new ResourceNotFoundException("user id", Userid));
		
		List<Post> posts = this.postRepository.findByUser(user);
		
		List<PostDto> collect = posts.stream().map((p)->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());

		return collect;
	}

	
	
	
	
	@Override
	public List<PostDto> searchPosts(String keyword) {


		List<Post> searchPosts = this.postRepository.findByTitleContaining(keyword);
		
		List<PostDto> collect = searchPosts.stream().map(p->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
		
		return collect;
	}

}
