package com.blog_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_app.exceptions.ApiException;
import com.blog_app.payloads.JwtAuthResponce;
import com.blog_app.payloads.JwtRequest;
import com.blog_app.payloads.UserDto;
import com.blog_app.security.CustomUserDetailsService;
import com.blog_app.security.JwtTokenHeper;
import com.blog_app.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtTokenHeper jwtTokenHeper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> createToken(@RequestBody JwtRequest jwtRequest)
	{
		System.out.println("authcontroller active ");
		this.authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		
		UserDetails loadUserByUsername = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String generateToken = this.jwtTokenHeper.generateToken(loadUserByUsername);
		
		JwtAuthResponce jwtAuthResponce=new JwtAuthResponce();
		jwtAuthResponce.setJwtToken(generateToken);
		
		UserDto userbyEmail = this.userService.getUserbyEmail(loadUserByUsername.getUsername());
		int user_id = userbyEmail.getUser_id();
		jwtAuthResponce.setUser_id(user_id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(jwtAuthResponce);
		
		
	}

	
	
	
//method to autheticate username and password
	private void authenticate(String username, String password) {

		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try
		{
		this.authenticationManager.authenticate(authenticationToken);
		}
		catch (BadCredentialsException e) {
		System.out.println("Invalid Detials !!");
		throw new ApiException("Invalid username or password !!");
	}
		
		
	}
	
	
//register new user api
	
@PostMapping("/register")	
public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto)
{
	
	UserDto createUser = this.userService.registerNewUser(userDto);
	
	return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
	
}
	
	
	
	
}
