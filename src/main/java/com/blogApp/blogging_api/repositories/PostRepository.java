package com.blogApp.blogging_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.blogging_api.entity.Category;
import com.blogApp.blogging_api.entity.Post;
import com.blogApp.blogging_api.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitle(String title);

}
