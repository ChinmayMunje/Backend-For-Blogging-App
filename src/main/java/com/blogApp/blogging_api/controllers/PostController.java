package com.blogApp.blogging_api.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogApp.blogging_api.dto.PostDTO;
import com.blogApp.blogging_api.entity.Post;
import com.blogApp.blogging_api.exception.ApiResponse;
import com.blogApp.blogging_api.payload.PostResponse;
import com.blogApp.blogging_api.services.FileService;
import com.blogApp.blogging_api.services.PostService;
import com.blogApp.blogging_api.utils.AppConstants;

@RestController
@RequestMapping(value = "/api/v1")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping(value = "/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDTO post = postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);

	}

	@PostMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Integer postId) {
		PostDTO post = postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
	}

	@GetMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
		PostDTO post = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.FOUND);
	}

	@GetMapping(value = "/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
			)

	{
		PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.FOUND);
	}

	@DeleteMapping(value = "/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping(value = "/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId) {
		List<PostDTO> post = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(post, HttpStatus.FOUND);
	}

	@GetMapping(value = "/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDTO> post = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(post, HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/posts/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keyword){
		List<PostDTO> post = postService.searchPost(keyword);
		return new ResponseEntity<List<PostDTO>>(post, HttpStatus.OK);
	}
	
	//METHOD TO POST IMAGE
	@PostMapping(value = "/posts/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws MalformedURLException, IOException{
		
		PostDTO postDTO = postService.getPostById(postId);  
		String fileName = fileService.uploadImage(path,image);
		
		postDTO.setImage(fileName);
		PostDTO update = postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(update, HttpStatus.OK);
		
	}
	
	//METHOD TO SERVE FILE
	
	@GetMapping(value = "/posts/image/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String image, HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResources(path, image);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
}
