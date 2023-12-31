package com.blogApp.blogging_api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.blogging_api.dto.CommentDTO;
import com.blogApp.blogging_api.entity.Comment;
import com.blogApp.blogging_api.entity.Post;
import com.blogApp.blogging_api.exception.BloggingException;
import com.blogApp.blogging_api.repositories.CommentRepository;
import com.blogApp.blogging_api.repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		
		Post post = postRepository.findById(postId).orElseThrow(()->new BloggingException("Post", "Post Id", postId));
		
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		
		comment.setPost(post);
		
		Comment saveComment = commentRepository.save(comment);
		// TODO Auto-generated method stub
		
		
		return modelMapper.map(saveComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		commentRepository.deleteById(commentId);
		
		
	}

}
