package com.blog_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app.payloads.CommentsDto;
import com.blog_app.services.CommentsService;

@RestController
@RequestMapping("/api")
public class CommentsController {

@Autowired	
private CommentsService commentsService;	


//create 
@PostMapping("/post/{postId}/comments")	
public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentsDto ,@PathVariable("postId") Integer postId)
{
	CommentsDto commentDto = this.commentsService.createComment(commentsDto, postId);
	
	return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
	
	
}
	
@DeleteMapping("/comments/{commId}")	
public ResponseEntity<String> deleteComment(@PathVariable("commId") Integer commtId)
{
	this.commentsService.deleteComment(commtId);
	
	return ResponseEntity.status(HttpStatus.ACCEPTED).body("comment deleted..!");
	
	
}	
	
	
	
	
	
	
	
}
