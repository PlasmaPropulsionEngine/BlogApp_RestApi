package com.blog_app.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	
private Integer cId;

@NotBlank(message = "field can not be blank")
@Size(min=5, message = "charater must  5+")
private String categoryTitle;
	
	
	
	
	
	
	
	
	
	
}
