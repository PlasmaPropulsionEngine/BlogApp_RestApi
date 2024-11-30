package com.blog_app.services;

import com.blog_app.payloads.CommentsDto;

public interface CommentsService {

	
	
 public  CommentsDto createComment(CommentsDto  commentsDto,Integer postId);
 
 public void deleteComment(Integer commId);
	
	
	
}
