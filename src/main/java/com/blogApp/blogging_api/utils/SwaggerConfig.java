package com.blogApp.blogging_api.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	
	private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
		
	}
	
	private List<SecurityReference> sf(){
		AuthorizationScope scopes = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
	}


	@Bean
	public Docket api() {
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getinfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
		
	}

	private ApiInfo getinfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Blogging Application: Backend Course", "This Project is Developed By Chinmay Munje", "1.0", "Terms of Service", new Contact("Chinmay", "https://learnprogramming.com","chinmaymunje@yahoo.com"), "License of API", "API License URL", Collections.emptyList());
		
	}
}
