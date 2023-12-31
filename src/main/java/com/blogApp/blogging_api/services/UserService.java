package com.blogApp.blogging_api.services;

import java.util.List;

import com.blogApp.blogging_api.dto.UserDTO;
import com.blogApp.blogging_api.exception.BloggingException;

public interface UserService {
	
	UserDTO registerNewUser(UserDTO userDTO);
	
	UserDTO createUser(UserDTO user) throws BloggingException;
	
	UserDTO updateUser(UserDTO user, Integer userId) throws BloggingException;
	
	UserDTO getUserById(Integer userId) throws BloggingException;
	
	List<UserDTO> getAllUsers() throws BloggingException;
	
	void deleteUser(Integer userId) throws BloggingException;	

}
