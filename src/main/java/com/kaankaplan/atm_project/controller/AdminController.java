package com.kaankaplan.atm_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaankaplan.atm_project.business.abstracts.AdminService;
import com.kaankaplan.atm_project.entity.Admin;

@RestController
@RequestMapping("/api/admins/")
@CrossOrigin
public class AdminController {

	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("delete/{adminId}")
	public ResponseEntity<String> delete(@PathVariable int adminId) {
		this.adminService.delete(adminId);
		
		return new ResponseEntity<String>("Kullanıcı başarı ile kayıt edildi.", HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("update")
	public ResponseEntity<String> update(@RequestBody Admin admin) {
		this.adminService.update(admin);
		
		return new ResponseEntity<String>("Kullanıcı başarı ile kayıt edildi.", HttpStatus.CREATED);
	}
}
