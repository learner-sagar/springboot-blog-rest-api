package com.sagar.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagar.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name); 
}