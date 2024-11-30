package com.blog_app.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post {

	
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer pId;

@Column(name = "title",nullable = false,length = 200)
private String title;

@Column(name = "content",nullable = false,length = 200)
private String content;

private String imagename;

private Date addedDate;

@ManyToOne
private Category category;

@ManyToOne
private User user;

@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
@JsonIgnore
private Set<Comments>comments=new HashSet<>();	
	
	
	
	
	
	
	
	
	
	
	
	
}
