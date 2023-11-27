package com.sagar.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagar.blog.entity.Comment;

public interface CommentRespository extends JpaRepository<Comment, Long>{
	List<Comment> findByPostId(Long postId);
}
