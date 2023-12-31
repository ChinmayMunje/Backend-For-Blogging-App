package com.blogApp.blogging_api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.blogApp.blogging_api.dto.CategoryDTO;
import com.blogApp.blogging_api.exception.ApiResponse;
import com.blogApp.blogging_api.services.CategoryService;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@PostMapping(value = "/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO cat = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(cat, HttpStatus.CREATED);
	};

	@PutMapping(value = "/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
		CategoryDTO cat = categoryService.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<CategoryDTO>(cat, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
		CategoryDTO cat = categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDTO>(cat, HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		List<CategoryDTO> cat = categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDTO>>(cat, HttpStatus.FOUND);
	}
	
	@DeleteMapping(value = "/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
		
	}
	
}
