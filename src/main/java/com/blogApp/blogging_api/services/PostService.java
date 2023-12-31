package com.blogApp.blogging_api.services;

import java.util.List;

import com.blogApp.blogging_api.dto.PostDTO;
import com.blogApp.blogging_api.payload.PostResponse;

public interface PostService {
	
	//CREATE
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	//UPDATE
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	//GET POST BY ID
	PostDTO getPostById(Integer postId);
	
	//GET ALL POSTS
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//DELETE POST
	void deletePost(Integer postId);
	
	//GET POST BY CATEGORY
	List<PostDTO> getPostByCategory(Integer categoryId);
	
	//GET POST BY USER
	List<PostDTO> getPostByUser(Integer userId);
	
	//GET POST BY SEARCH
	List<PostDTO> searchPost(String keyword);
	
	

}
