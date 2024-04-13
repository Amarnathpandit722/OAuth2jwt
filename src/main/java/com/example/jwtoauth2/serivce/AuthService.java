package com.example.jwtoauth2.serivce;

import org.springframework.security.core.Authentication;

import com.example.jwtoauth2.dto.AuthResponseDto;
import com.example.jwtoauth2.dto.UserRegistrationDto;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
	
	AuthResponseDto  getJwtTokensAfterAuthentication(Authentication authentication, HttpServletResponse response);
	Object getAccessTokenUsingRefreshToken(String authorizationHeader);
	AuthResponseDto registerUser(UserRegistrationDto userRegistrationDto, HttpServletResponse httpServletResponse);
}
