package com.blog_app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog_app.config.AppConstants;
import com.blog_app.payloads.ApiResponse;
import com.blog_app.payloads.PostDto;
import com.blog_app.payloads.PostResponse;
import com.blog_app.repository.PostRepository;
import com.blog_app.services.FileService;
import com.blog_app.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	String path;
	
//create post	
	@PostMapping("/user/{UserId}/category/{CategoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("CategoryId")  Integer CategoryId,@PathVariable("UserId") Integer UserId)
	{
		
		PostDto createPost = this.postService.createPost(postDto,CategoryId ,UserId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
		
	}

//update post	
@PutMapping("/posts/{postId}")	
public ResponseEntity<PostDto> updatePost(@RequestBody  PostDto postDto,@PathVariable("postId")  Integer postId)
{
	PostDto updatePost = this.postService.updatePost(postDto, postId);
	
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatePost);
	
} 

//delete post by post id
@DeleteMapping("/posts/{postId}")	
public ApiResponse DeletePost(@PathVariable("postId") Integer postId)
{
	this.postService.deletePost(postId);
	//return ResponseEntity.status(HttpStatus.OK).body("Deleted ");
	return new ApiResponse("Post deleted succesfully!",true);
}
	
		
	
//get ALL posts by category Id	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategories(@RequestBody PostDto postDto, @PathVariable("categoryId") Integer categoryId)
	{
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(postByCategory);
	}
	
//get ALL posts by User Id
@GetMapping("user/{userId}/posts")	
public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId)
{
	List<PostDto> postByUser = this.postService.getPostByUser(userId);
	
	
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(postByUser);
	
}

//get all posts

@GetMapping("/posts")
public ResponseEntity<PostResponse> getAllPosts(
		@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
		@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
		@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy)
{
	 PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy);
	
	return ResponseEntity.status(HttpStatus.OK).body(allPost);
}

//get post by post id 

@GetMapping("/posts/{postId}")
public ResponseEntity<PostDto> getPostByPostId(@PathVariable("postId") Integer postId)
{
	
	PostDto postDto = this.postService.getPostByid(postId);
	
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(postDto);
}

//search posts
@GetMapping("/posts/search/{keyword}")
public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword)
{
		
	List<PostDto> searchPosts = this.postService.searchPosts(keyword);
	
	return ResponseEntity.status(HttpStatus.OK).body(searchPosts);
	
}


//post image upload
@PostMapping("/post/image/upload/{postId}")
public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable("postId") Integer PostId) throws IOException
{
	PostDto postDto = this.postService.getPostByid(PostId);
	
	String filename = this.fileService.uploadImage(path, image);
	
	System.out.println(filename);
	
	postDto.setImagename(filename);
	
	PostDto updatePost = this.postService.updatePost(postDto, PostId);
	
	return	ResponseEntity.status(HttpStatus.ACCEPTED).body(updatePost);
	
}


//method to serve files
@GetMapping(value = "/post/image/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
public void downloadImage(@PathVariable("imagename") String imagename, HttpServletResponse response) throws IOException {

    InputStream resource = this.fileService.getResource(path, imagename);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource,response.getOutputStream());

}


















	
	
}
