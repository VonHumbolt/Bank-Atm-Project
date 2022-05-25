package com.kaankaplan.atm_project.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kaankaplan.atm_project.business.abstracts.AuthService;
import com.kaankaplan.atm_project.business.abstracts.OperationClaimService;
import com.kaankaplan.atm_project.business.abstracts.SystemUserService;
import com.kaankaplan.atm_project.business.abstracts.UserService;
import com.kaankaplan.atm_project.entity.OperationClaim;
import com.kaankaplan.atm_project.entity.SystemUser;
import com.kaankaplan.atm_project.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthManager implements AuthService, UserDetailsService {

	private UserService userService;
	private SystemUserService systemUserService;
	private OperationClaimService operationClaimService;
	
	@Autowired
	public AuthManager(UserService userService, SystemUserService systemUserService, 
			OperationClaimService operationClaimService) {
		this.userService = userService;
		this.systemUserService = systemUserService;
		this.operationClaimService = operationClaimService;
	}
	
	@Override
	public void register(User user) {
		log.info("-> Register");
		
		this.userService.add(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		SystemUser systemUser = this.systemUserService.getSystemUserByEmail(email);
		
		if (systemUser == null) {
			throw new UsernameNotFoundException("Kullanıcı bulunamadı");
		}
		
		OperationClaim claim = this.operationClaimService.getClaimByEmail(email);
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(claim.getClaimName()));
		
		
		return new org.springframework.security.core.userdetails.User(systemUser.getEmail(), systemUser.getPassword(), authorities);
	}

	
}
