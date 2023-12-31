package com.blogApp.blogging_api.services;

import java.util.List;

import com.blogApp.blogging_api.dto.CategoryDTO;

public interface CategoryService {

	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
	
	CategoryDTO getCategory(Integer categoryId);
	
	List<CategoryDTO> getAllCategory();
	
	void deleteCategory(Integer categoryId);

}
