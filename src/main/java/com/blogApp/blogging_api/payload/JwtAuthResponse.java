package com.blogApp.blogging_api.payload;

import com.blogApp.blogging_api.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
	
	private String token;
	
	private UserDTO user;

}
