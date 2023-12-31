package com.blogApp.blogging_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.blogging_api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
