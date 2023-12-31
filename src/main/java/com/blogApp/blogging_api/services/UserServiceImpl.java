package com.blogApp.blogging_api.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApp.blogging_api.dto.UserDTO;
import com.blogApp.blogging_api.entity.Role;
import com.blogApp.blogging_api.entity.User;
import com.blogApp.blogging_api.exception.BloggingException;
import com.blogApp.blogging_api.repositories.RoleRepository;
import com.blogApp.blogging_api.repositories.UserRepository;
import com.blogApp.blogging_api.utils.AppConstants;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDTO createUser(UserDTO user) {
		User newUser = dtoToUser(user);
		User savedUser = userRepository.save(newUser);
		return userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) {
		// TODO Auto-generated method stub
		User newUser = userRepository.findById(userId).orElseThrow(()-> new BloggingException("User", "Id", userId));
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		User updatedUser = userRepository.save(newUser);
		return userToDto(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User newUser = userRepository.findById(userId).orElseThrow(()-> new BloggingException("User", "Id", userId));		
		return userToDto(newUser);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> newUser = userRepository.findAll();
		List<UserDTO> listdDTO = newUser.stream().map((user)->userToDto(user)).collect(Collectors.toList()); 
		return listdDTO;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}
	
	private User dtoToUser(UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setUsername(userDto.getUsername());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDTO userToDto(User user) {
		UserDTO userdto = modelMapper.map(user, UserDTO.class);
		return userdto;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		User user = modelMapper.map(userDTO, User.class);
		
		// ENCODED PASSWORD WHILE REGISTERING NEW USER
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//ROLES
		Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		
		User newUser = userRepository.save(user);
		
		return modelMapper.map(newUser, UserDTO.class);
	}
}
