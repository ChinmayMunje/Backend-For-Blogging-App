package com.blogApp.blogging_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.blogging_api.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	

}
