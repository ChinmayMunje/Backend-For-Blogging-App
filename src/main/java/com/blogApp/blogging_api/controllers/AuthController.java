package com.blogApp.blogging_api.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.blogging_api.dto.UserDTO;
import com.blogApp.blogging_api.entity.User;
import com.blogApp.blogging_api.exception.ApiException;
import com.blogApp.blogging_api.payload.JwtAuthRequest;
import com.blogApp.blogging_api.payload.JwtAuthResponse;
import com.blogApp.blogging_api.security.JwtTokenHelper;
import com.blogApp.blogging_api.services.UserService;

@RestController
@RequestMapping(value = "/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	////// LOGIN API WITH AUTHENTICATION
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
		
		authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		
		String token = jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		
		response.setToken(token);
		response.setUser(mapper.map((User) userDetails, UserDTO.class));
		
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		
	}


	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("Invalid Details !!!");
			throw new ApiException("Invalid Username or Password !!");
		}
		
		
	}
	
	
	/////// REGISTER NEW USER API USING AUTHENTICATION
	
	@PostMapping(value = "/registerUser")
	public ResponseEntity<UserDTO> registerNewUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO registeredUser = userService.registerNewUser(userDTO);
		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);
	}

}
