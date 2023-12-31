package com.blogApp.blogging_api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.blogApp.blogging_api.entity.Category;
import com.blogApp.blogging_api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	
	private Integer postId;

	private String title;
	
	private String content;
	
	private String image;

	private Date addedDate;

	private CategoryDTO category;

	private UserDTO user;
	
	private List<CommentDTO> comments = new ArrayList<>();
	

}
