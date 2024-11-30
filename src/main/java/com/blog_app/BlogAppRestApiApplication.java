package com.blog_app;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog_app.config.AppConstants;
import com.blog_app.models.Roles;
import com.blog_app.repository.RoleRepository;


import jakarta.validation.constraints.AssertFalse.List;



@SpringBootApplication
public class BlogAppRestApiApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppRestApiApplication.class, args);
	}
 
	 
	@Bean
	public ModelMapper modleMapper()
	{
		return new ModelMapper();
		
	}

@Autowired 
private	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
	
		System.out.println(this.passwordEncoder.encode("test"));
		
		try {
			Roles role=new Roles();
			
			role.setRoleId(AppConstants.NORMAL_USER);
			role.setRolename("ROLE_NORMAL");
			Roles role1=new Roles();
			role1.setRoleId(AppConstants.ADMIN_USER);
			role1.setRolename("ROLE_ADMIN");
			
			java.util.List<Roles> listOfRole = java.util.List.of(role,role1); 
			
			java.util.List<Roles> saveAll = this.roleRepository.saveAll(listOfRole);
			
			saveAll.stream().forEachOrdered(s->System.out.println(s.getRolename()));
			
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		
		
		
	}
	
	
}
