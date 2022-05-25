package com.kaankaplan.atm_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operation_claim")
public class OperationClaim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "claim_id")
	private int claimId;
	
	@Column(name = "claim_name")
	private String claimName;
	
	@OneToOne(mappedBy = "operationClaim")
	@JsonIgnore
	private SystemUser systemUser;
	
//	@OneToOne(mappedBy = "operationClaim")
//	@JsonIgnore
//	private Admin admin;
	
}
