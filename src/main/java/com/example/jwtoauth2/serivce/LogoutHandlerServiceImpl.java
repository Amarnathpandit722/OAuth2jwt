package com.example.jwtoauth2.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.example.jwtoauth2.dto.TokenType;
import com.example.jwtoauth2.repo.RefreshTokenRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class LogoutHandlerServiceImpl implements LogoutHandler {
	@Autowired
	 private RefreshTokenRepo refreshTokenRepo;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

	    if (authHeader == null || !authHeader.startsWith(TokenType.Bearer.name())) {
	        return;
	    }

	    final String refreshToken = extractToken(authHeader);
	    
	    refreshTokenRepo.findByRefreshToken(refreshToken).ifPresent(token -> {
	        token.setRevoked(true);
	        refreshTokenRepo.save(token);
	    });
	}

	private String extractToken(String authHeader) {
	    return authHeader.substring(TokenType.Bearer.name().length()).trim();
	}
}
