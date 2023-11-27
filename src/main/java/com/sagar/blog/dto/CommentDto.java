package com.sagar.blog.dto;

import lombok.Data;

@Data
public class CommentDto {
	private long id;
	private String body;
	private String name;
	private String email;
}
