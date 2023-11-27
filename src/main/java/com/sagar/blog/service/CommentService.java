package com.sagar.blog.service;

import java.util.List;

import com.sagar.blog.dto.CommentDto;

public interface CommentService {
	CommentDto createComment(Long postId, CommentDto commentDto);
	List<CommentDto> getCommentsByPostId(Long postId);
	CommentDto getCommentById(Long postId, Long id);
	CommentDto updateComment(Long postId, Long id, CommentDto commentDto);
	void deleteComment(Long postId,Long id);
}
