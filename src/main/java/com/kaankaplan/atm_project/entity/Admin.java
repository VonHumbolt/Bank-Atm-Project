package com.kaankaplan.atm_project.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Admin extends SystemUser { // BANKA

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "admin_id")
//	private int adminId;
	
	@Column(name = "bank_name")
	private String bankName;
	
//	@Column(name = "email")
//	private String email;
//	
//	@Column(name = "password")
//	private String password;
	
	@OneToMany(mappedBy = "admin")
	private List<User> users;
	
//	@OneToOne
//	@JoinColumn(name = "claim_id")
//	private OperationClaim operationClaim;
}
