package com.blog_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app.payloads.UserDto;
import com.blog_app.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController
{
	@Autowired(required = true)
	private UserService userService; 
	
//post-  create user
@Operation(summary = "creats user")	
@PostMapping("/users")	
public ResponseEntity<UserDto> createUser(@Valid   @RequestBody  UserDto usrDto )
{

	
	UserDto userdto = this.userService.createUser(usrDto);
	
	return ResponseEntity.status(HttpStatus.CREATED).body(userdto);
}	
// put -update user

@PutMapping("/users/{id}")
public ResponseEntity<UserDto> updateUser(@Valid  @RequestBody UserDto userDto,@PathVariable("id") Integer id)
{
	
	UserDto updateUser = this.userService.updateUser(userDto, id);
	
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);
		
}


//Admin authority
//delete user
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/users/{id}")
public ResponseEntity deleteUser(@PathVariable("id") Integer id)
{
	this.userService.deleteUserDto(id);
	
	 return ResponseEntity.ok("user deleted");	


	
}

//get all users 
@GetMapping("/users")
public ResponseEntity<List<UserDto>> getAllUser()
{
	
	List<UserDto> allUser = this.userService.getAllUser();
	
	
	return ResponseEntity.status(HttpStatus.OK).body(allUser);
	
}

//get single user by id
@GetMapping("/users/{id}")
public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id )
{
	
		UserDto userDtoById = this.userService.getUserDtoById(id);
	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDtoById);
	
}



}




