package com.blog_app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog_app.exceptions.ResourceNotFoundException;
import com.blog_app.models.User;
import com.blog_app.repository.UserRepository;
import com.blog_app.services.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository  userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException(username,0));
		
		return user;
	}

}
