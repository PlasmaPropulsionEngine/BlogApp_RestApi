package com.blog_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blog_app.security.CustomUserDetailsService;
import com.blog_app.security.JwtAuthenticationEntryPoint;
import com.blog_app.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc 
@EnableMethodSecurity
public class SecurityConfig {

@Autowired	
private CustomUserDetailsService customUserDetailsService;	
	
@Autowired	
private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

@Autowired
private JwtAuthenticationFilter jwtauthenticationFilter;
	
	private static final String [] PUBLIC_URLS= {"/api/v1/auth/**","/api/posts","/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"};


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(handling -> handling.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 
		 		http.addFilterBefore(this.jwtauthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		 		http.authenticationProvider(daoAuthenticationProvider());    
	    
		 		DefaultSecurityFilterChain defaultSecurityFilterChain =http.build();
		 		return defaultSecurityFilterChain;
	}
	
	
	  	@Bean
	    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }

	    @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider() {

	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(this.customUserDetailsService);
	        provider.setPasswordEncoder(passwordEncoder());
	        return provider;

	    }

	
	  	@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }	
	
	
	
	
	
	
	
	
	
	
	
}
