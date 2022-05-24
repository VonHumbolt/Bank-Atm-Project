package com.kaankaplan.atm_project.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kaankaplan.atm_project.business.abstracts.AuthService;
import com.kaankaplan.atm_project.business.abstracts.UserService;
import com.kaankaplan.atm_project.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthManager implements AuthService, UserDetailsService {

	private UserService userService;
	
	@Autowired
	public AuthManager(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void register(User user) {
		log.info("-> Register");
		
		this.userService.add(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// Ortak bir sınıf, user ve admin için ???
		return null;
	}

	
}
