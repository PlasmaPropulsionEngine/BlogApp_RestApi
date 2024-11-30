package com.blog_app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.models.Comments;
import com.blog_app.models.Post;
import com.blog_app.payloads.CommentsDto;
import com.blog_app.repository.CommentsRepository;
import com.blog_app.repository.PostRepository;
import com.blog_app.services.CommentsService;

@Service
public class CommentsServiceimpl implements CommentsService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post ", postId));
		
		Comments comments = this.modelMapper.map(commentsDto, Comments.class);
		
		comments.setPost(post);
		Comments save = this.commentsRepository.save(comments);

		return this.modelMapper.map(save,CommentsDto.class);
	}

	@Override
	public void deleteComment(Integer commId) {
	
		Comments comment = this.commentsRepository.findById(commId).orElseThrow(()->new ResourceNotFoundException("comment",commId));
		this.commentsRepository.delete(comment);
		
		
		
	}

}
