package com.blog_app.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer cId;


@Column( nullable = false)
private String categoryTitle;

	
@OneToMany(mappedBy ="category",cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
@JsonIgnore
private List<Post>post=new ArrayList<>();	
	
	
	
	
	
	
	
	
	
}
