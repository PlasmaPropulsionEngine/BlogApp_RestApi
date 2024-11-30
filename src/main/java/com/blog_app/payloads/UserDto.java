package com.blog_app.payloads;

import java.util.HashSet;
import java.util.Set;

import com.blog_app.models.Roles;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto
{
	
private int  user_id;

@Email(message = "invalid email")
@NotBlank(message = "filed can not be empty !!")
@Size(min=5,message = "email size min. 5")
@Column(name = "Email", nullable = false,unique =true)
private String email;


@NotBlank(message = "filed can not be empty !!")
@Size(min=3,max = 12, message = "password must be between 3 to 12 charaters !!")
@Column(name = "Password")
private String password;


@NotBlank(message = "filed can not be empty !!")
@Size(min=3,max = 8,message = "name should i between 3 and 8")
@Column(name = "name")
private String name;
	
	
private Set<Roles>roles=new HashSet<>();	
	
	
}
