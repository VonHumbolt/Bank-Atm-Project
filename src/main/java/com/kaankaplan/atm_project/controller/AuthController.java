package com.kaankaplan.atm_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaankaplan.atm_project.business.abstracts.AuthService;
import com.kaankaplan.atm_project.entity.User;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class AuthController {

	private AuthService authService;
	
	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody User user) {
		this.authService.register(user);
		
		return new ResponseEntity<String>("Kullanıcı başarı ile kayıt edildi.", HttpStatus.CREATED);
	}
}
