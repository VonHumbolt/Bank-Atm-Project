package com.kaankaplan.atm_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.kaankaplan.atm_project.business.abstracts.UserService;
import com.kaankaplan.atm_project.entity.User;


@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("getall")
	public ResponseEntity<List<User>> getallUsers(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize) {
		
		return ResponseEntity.ok(this.userService.getallUsers(
				pageNo.orElse(1), pageSize.orElse(10)));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("getByUserId/{userId}")
	public ResponseEntity<User> getByUserId(@PathVariable int userId) {
		return ResponseEntity.ok(this.userService.getByUserId(userId));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("delete/{userId}")
	public ResponseEntity<String> delete(@PathVariable int userId) {
		this.userService.delete(userId);
		
		return new ResponseEntity<String>("İşlem başarılı", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("update")
	public ResponseEntity<String> update(@RequestBody User user) {
		
		this.userService.update(user);
		
		return new ResponseEntity<String>("İşlem başarılı", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@PostMapping("withDrawMoney/{accountNumber}/{money}")
	public ResponseEntity<String> withdrawMoney(@PathVariable String accountNumber, @PathVariable double money) {
		this.userService.withdrawMoney(accountNumber, money);
		
		return new ResponseEntity<String>("İşlem başarılı", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("loadMoney/{accountNumber}/{money}")
	public ResponseEntity<String> loadMoney(@PathVariable String accountNumber, @PathVariable double money) {
		this.userService.loadMoney(accountNumber, money);
		
		return new ResponseEntity<String>("İşlem başarılı", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@PostMapping("transferMoney/{fromAccountNumber}/{toAccountNumber}/{money}")
	public ResponseEntity<String> transferMoney(@PathVariable String fromAccountNumber, @PathVariable String toAccountNumber, @PathVariable double money) {
		
		this.userService.transferMoney(fromAccountNumber, toAccountNumber, money);
		
		return new ResponseEntity<String>("İşlem başarılı", HttpStatus.OK);
	}
}
