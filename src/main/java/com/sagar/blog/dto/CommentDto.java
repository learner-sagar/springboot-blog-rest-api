package com.sagar.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
	private long id;
	
	@NotEmpty
	@Size(min = 10, message = "Comment body myl be minimum 10 characters")
	private String body;
	
	@NotEmpty(message = "Name should not be null or empty")
	private String name;
	
	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;
}
