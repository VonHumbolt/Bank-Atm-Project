package com.kaankaplan.atm_project.security.filter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaankaplan.atm_project.entity.dto.LoginRequestDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class UsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	@Autowired
	public UsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			LoginRequestDto loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
			
			String email = loginRequest.getEmail();
			String password = loginRequest.getPassword();
			
			Authentication authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
			
			return authenticationManager.authenticate(authenticationToken);
			
		} catch (IOException e) {
			throw new RuntimeException("Authentication Failed!");
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String key = "secureKeysecureKeysecureKeysecureKeysecureKeysecureKeysecureKeysecureKey";
		
		String token = Jwts.builder()
				.setSubject(authResult.getName())
				.claim("authorities ", authResult.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
				.signWith(Keys.hmacShaKeyFor(key.getBytes()))
				.compact();
		
		response.addHeader("Authorization", "Bearer " + token);
	}
}
