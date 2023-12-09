package com.sagar.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagar.blog.dto.CommentDto;
import com.sagar.blog.entity.Comment;
import com.sagar.blog.entity.Post;
import com.sagar.blog.execption.BlogApiException;
import com.sagar.blog.execption.ResourceNotFoundException;
import com.sagar.blog.repository.CommentRespository;
import com.sagar.blog.repository.PostRepository;
import com.sagar.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	private CommentRespository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;
	public CommentServiceImpl(CommentRespository commentRespository, PostRepository postRepository, ModelMapper mapper) {
		this.commentRepository = commentRespository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}
	
	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Comment comment = mapToEntity(commentDto);
		
		//retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post", "id", postId));
		comment.setPost(post);
		
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
	}
	
	@Override
	public List<CommentDto> getCommentsByPostId(Long postId){
		List<Comment> comments = commentRepository.findByPostId(postId);
		return comments.stream().map((comment) -> mapToDto(comment)).collect(Collectors.toList());
	}
	
	@Override
	public CommentDto getCommentById(Long postId, Long id) {
		Comment comment = validateComment(postId, id);
		return mapToDto(comment);
	}
	
	@Override
	public CommentDto updateComment(Long postId, Long id, CommentDto commentDto){
		Comment comment = validateComment(postId, id);
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
		Comment updatedComment = commentRepository.save(comment);
		return mapToDto(updatedComment);
	}
	
	@Override
	public void deleteComment(Long postId, Long id) {
		Comment comment = validateComment(postId, id);
		commentRepository.delete(comment);
	}
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setBody(comment.getBody());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto, Comment.class);
//		Comment comment = new Comment();
//		comment.setBody(commentDto.getBody());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
		return comment;
	}
	
	private Comment validateComment(Long postId, Long id) {
		Post post = postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post", "Id", postId));
		Comment comment = commentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Comment", "Id", id));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return comment;
	}
}
