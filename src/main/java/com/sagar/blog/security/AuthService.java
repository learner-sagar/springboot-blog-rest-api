package com.sagar.blog.security;

import com.sagar.blog.dto.LoginDto;
import com.sagar.blog.dto.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
	String register(RegisterDto registerDto);
}
