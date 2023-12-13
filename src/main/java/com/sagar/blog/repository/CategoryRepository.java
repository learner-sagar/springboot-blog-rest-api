package com.sagar.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagar.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
