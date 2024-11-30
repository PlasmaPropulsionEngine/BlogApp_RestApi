package com.blog_app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog_app.config.AppConstants;
import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.models.Roles;
import com.blog_app.models.User;
import com.blog_app.payloads.UserDto;
import com.blog_app.repository.RoleRepository;
import com.blog_app.repository.UserRepository;
import com.blog_app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private	UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//user save
	@Override
	public UserDto createUser(UserDto userDto) 
	{
		User user = this.UserDtoToUser(userDto);
		//user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		User userSaved = this.userRepository.save(user);
		UserDto userToUserDto = this.UserToUserDto(userSaved);
		return userToUserDto;
	}

	//user update
	@Override
	public UserDto updateUser(UserDto userDto, Integer id)
	{
	
			User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", id));
		
			user.setEmail(userDto.getEmail());
			user.setName(userDto.getName());
			user.setPassword(userDto.getPassword());
		
		User save = this.userRepository.save(user);
		
		UserDto userToUserDto = this.UserToUserDto(save);
		
		return userToUserDto;
	}

//Get user by user id 	
	@Override
	public UserDto getUserDtoById(Integer id) {
	
		User user = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User",id));
		
		UserDto userDto = UserToUserDto(user);
		return userDto;
		
	}

	
//get all users	
	
	@Override
	public List<UserDto> getAllUser() {
	
		List<User> users = this.userRepository.findAll();
		
		List<UserDto> collect = users.stream().map(s->this.UserToUserDto(s)).collect(Collectors.toList());
		
		return collect;
	}


//delete user by user id	
	@Override
	public void deleteUserDto(Integer id) {
	
		 User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", id));
		 
		 this.userRepository.delete(user);
		 
//		user.setUser_id(id);
//		this.userRepository.deleteById(user.getUser_id());
	}

// this methods used for UserDto  and Entity User Conversions
//also we can use object mapper class	
	private User UserDtoToUser(UserDto userDto)
	{
		User user =this.modelMapper.map(userDto,User.class);
		
//		user.setUser_id(userDto.getUser_id());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	
	private UserDto UserToUserDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		
		
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setName(user.getName());
//		userDto.setUser_id(user.getUser_id());
		return userDto;
		
	}

	//register method
	@Override
	public UserDto registerNewUser(UserDto userDto)
	{		
		User user = this.modelMapper.map(userDto,User.class);
		
		//password encoded
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//role set
		Roles roles = this.roleRepository.findById(AppConstants.NORMAL_USER).get();
	
		user.getRoles().add(roles);
		
		User save = this.userRepository.save(user);
		
		return this.modelMapper.map(save,UserDto.class);
	}

	
//get user by email id	
	@Override
	public UserDto getUserbyEmail(String email) {
	
		User user = this.userRepository.findByEmail(email).get();
		
		return this.modelMapper.map(user,UserDto.class);
		
	}
	
	
	
	
}
