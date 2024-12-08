package com.blog_app;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer
{

	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(BlogAppRestApiApplication.class);  // Specify the main application class
	    }	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
