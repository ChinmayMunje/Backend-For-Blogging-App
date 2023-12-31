package com.blogApp.blogging_api.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.blogging_api.dto.CategoryDTO;
import com.blogApp.blogging_api.entity.Category;
import com.blogApp.blogging_api.exception.BloggingException;
import com.blogApp.blogging_api.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category addedCategory = categoryRepository.save(category);
		
		return modelMapper.map(addedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new BloggingException("Category", "Category Id", categoryId));
		
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updtedCategory =  categoryRepository.save(category);
		return modelMapper.map(updtedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new BloggingException("Category", "Category Id", categoryId));
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> list = categoryRepository.findAll();
		List<CategoryDTO> listCatDTO = list.stream().map((cat)->modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return listCatDTO;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryRepository.deleteById(categoryId);
		// TODO Auto-generated method stub
		
	}

}
