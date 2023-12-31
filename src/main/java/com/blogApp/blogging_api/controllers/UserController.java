package com.blogApp.blogging_api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.blogging_api.dto.UserDTO;
import com.blogApp.blogging_api.exception.ApiResponse;
import com.blogApp.blogging_api.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
		UserDTO createdUser = userService.createUser(userDto);
		return new ResponseEntity<UserDTO>(createdUser, HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Integer userId){
		UserDTO updatedUser = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
		UserDTO getById = userService.getUserById(userId);
		return new ResponseEntity<UserDTO>(getById, HttpStatus.FOUND);
		
	}
	
	/// HANDLED BY ADMIN
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> userList = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
	}

}
