package com.blog_app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ResourceNotFoundException  extends RuntimeException{


private String resourcename;

private int userid;

public ResourceNotFoundException(String resourcename, int userid) {
	super(String.format(" not found with " + resourcename +" ID "+ userid));
	this.resourcename = resourcename;
	this.userid = userid;
}



	
	
	
	
	
	
	
}
