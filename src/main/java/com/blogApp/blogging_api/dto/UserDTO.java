package com.blogApp.blogging_api.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.blogApp.blogging_api.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	private int id;
    @NotBlank(message = "Username is required")
	@Size( min = 6, message = "Username must be of 6 Characters !!")
	private String username;
    @NotBlank(message = "Email is required")
	@Email(message = "Email Address is Not Valid")
	private String email;
    @NotBlank(message = "Password is required")
	@Size(min = 4, message = "Password must be Min of 4 Character")
	private String password;
	
	private List<RoleDTO> roles = new ArrayList<>();
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}


}
