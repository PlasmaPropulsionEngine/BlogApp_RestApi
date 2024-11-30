package com.blog_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog_app.payloads.UserDto;


public interface UserService {

	
public UserDto registerNewUser(UserDto userDto);	
	
	
public 	UserDto	createUser(UserDto userDto);
	
public 	UserDto updateUser(UserDto userDto, Integer id);
	
public	UserDto  getUserDtoById(Integer id);
			
public	List<UserDto>getAllUser();
	
public	void deleteUserDto(Integer id);

public UserDto getUserbyEmail(String email);
}
