package com.blogApp.blogging_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.blogging_api.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
