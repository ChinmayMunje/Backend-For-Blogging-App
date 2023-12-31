package com.blogApp.blogging_api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogApp.blogging_api.entity.Role;
import com.blogApp.blogging_api.repositories.RoleRepository;
import com.blogApp.blogging_api.utils.AppConstants;

@SpringBootApplication
public class BloggingApiApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(passwordEncoder.encode("raj@1234"));
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");
			
			List<Role> roles = List.of(role,role1);
			
			List<Role> result = roleRepository.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	


}
