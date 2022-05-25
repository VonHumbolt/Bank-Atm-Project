package com.kaankaplan.atm_project.entity;


import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends SystemUser { // CUSTOMER

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "user_id")
//	private int userId;
//	
	@NotBlank
	@NotEmpty
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@NotEmpty
	@Column(name = "last_name")
	private String lastName;
	
//	@Email
//	@Column(name = "email")
//	private String email;
//	
//	@NotBlank
//	@NotEmpty
//	@Size(min = 4)
//	@Column(name = "password")
//	private String password;
	
	@Column(name = "account_number")
	private String acountNumber = getRandomAccountNumber();
	
	@Column(name = "is_account_blocked")
	private boolean isAccountBlocked = true;
	
	@Column(name = "number_of_attempt")
	private int numberOfAttempt = 3;
	
	@Column(name = "money")
	private double money;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	
//	@OneToOne
//	@JoinColumn(name = "claim_id")
//	private OperationClaim operationClaim;
	
	String getRandomAccountNumber() {
		Random random = new Random();
		int number = random.nextInt(99999999);
		
		return String.format("%06d", number);
	}
}
