package com.blogApp.blogging_api.services;

import com.blogApp.blogging_api.dto.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentDTO, Integer postId);
	
	void deleteComment(Integer commentId);

}
