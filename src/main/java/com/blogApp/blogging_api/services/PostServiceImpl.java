package com.blogApp.blogging_api.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blogApp.blogging_api.dto.PostDTO;
import com.blogApp.blogging_api.entity.Category;
import com.blogApp.blogging_api.entity.Post;
import com.blogApp.blogging_api.entity.User;
import com.blogApp.blogging_api.exception.BloggingException;
import com.blogApp.blogging_api.payload.PostResponse;
import com.blogApp.blogging_api.repositories.CategoryRepository;
import com.blogApp.blogging_api.repositories.PostRepository;
import com.blogApp.blogging_api.repositories.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new BloggingException("User", "User Id", userId));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new BloggingException("Category", "Category Id", categoryId));

		Post post = modelMapper.map(postDTO, Post.class);
		post.setImage("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post post1 = postRepository.save(post);
		// TODO Auto-generated method stub
		return modelMapper.map(post1, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElseThrow(() -> new BloggingException("Post", "Post Id", postId));

		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImage(postDTO.getImage());

		Post updatePost = postRepository.save(post);
		return modelMapper.map(updatePost, PostDTO.class);
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElseThrow(() -> new BloggingException("Post", "Post Id", postId));
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub

		//////// PAGINATION To SEE LIMITED POST ON SINGLE PAGE STARTS HERE /////////
//		int pageSize = 5;
//		int pageNumber = 1;
		
		org.springframework.data.domain.Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = org.springframework.data.domain.Sort.by(sortBy).ascending();
		}else {
			sort = org.springframework.data.domain.Sort.by(sortBy).descending();
		}

		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = postRepository.findAll(p);
		List<Post> list = pagePost.getContent();

		//////// PAGINATION To SEE LIMITED POST ON SINGLE PAGE ENDS HERE /////////

		List<PostDTO> postDtos = list.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		postRepository.deleteById(postId);

	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new BloggingException("Category", "Category Id", categoryId));
		List<Post> list = postRepository.findByCategory(category);
		List<PostDTO> postDtos = list.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(() -> new BloggingException("User", "User Id", userId));

		List<Post> list = postRepository.findByUser(user);
		List<PostDTO> postDtos = list.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> post = postRepository.findByTitle(keyword);
		List<PostDTO> postDtos = post.stream().map((posts)->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
