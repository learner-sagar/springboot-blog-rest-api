package com.sagar.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.blog.dto.CommentDto;
import com.sagar.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
			@Valid @RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable(value="postId") Long postId){
		return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentByPostIdAndCommentId(@PathVariable(value = "postId") Long postId, @PathVariable(value="id") Long id){
		return new ResponseEntity<CommentDto>(commentService.getCommentById(postId, id), HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(
			@PathVariable(value = "postId") Long postId, 
			@PathVariable(value = "id") Long id, 
			@Valid @RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.updateComment(postId, id, commentDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(
			@PathVariable(value="postId") Long postId,
			@PathVariable(value="id") Long id){
		commentService.deleteComment(postId, id);
		return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
	}
}