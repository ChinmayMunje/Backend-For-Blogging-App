package com.blogApp.blogging_api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	private Integer categoryId;
	@NotBlank
	private String categoryTitle;
	@NotBlank
	private String categoryDescription;

}
