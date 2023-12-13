package com.sagar.blog.service;

import java.util.List;

import com.sagar.blog.dto.PostDto;
import com.sagar.blog.dto.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	PostDto getPostById(Long id);
	PostDto updatePost(PostDto postDto, Long postId);
	void deletePostById(Long postId);
	List<PostDto> getPostsByCategory(Long categoryId);
}
