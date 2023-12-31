package com.blogApp.blogging_api.payload;

import java.util.List;

import com.blogApp.blogging_api.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
	
	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	 
	

}
