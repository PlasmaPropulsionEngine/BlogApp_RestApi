package com.blog_app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog_app.models.Category;
import com.blog_app.models.Comments;
import com.blog_app.models.User;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostDto {

	
private Integer pId;

private String title;

private String content;
	
private String imagename;

private Date addedDate;


private CategoryDto category;


private UserDto user;
	
private Set<CommentsDto> comments=new HashSet<>();

}
