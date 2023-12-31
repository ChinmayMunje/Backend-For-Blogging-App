package com.blogApp.blogging_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.blogging_api.dto.CommentDTO;
import com.blogApp.blogging_api.exception.ApiResponse;
import com.blogApp.blogging_api.services.CommentService;

@RestController
@RequestMapping(value = "/api/v1")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping(value = "/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId){
		CommentDTO commentDTO2 = commentService.createComment(commentDTO, postId);
		return new ResponseEntity<CommentDTO>(commentDTO2, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Delete Successfully !!!", true), HttpStatus.OK);
	}
	

}
