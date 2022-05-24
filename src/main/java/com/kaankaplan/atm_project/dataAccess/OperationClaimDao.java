package com.kaankaplan.atm_project.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kaankaplan.atm_project.entity.OperationClaim;

public interface OperationClaimDao extends JpaRepository<OperationClaim, Integer>{

	@Query("From OperationClaim o where o.claimName = :claimName")
	OperationClaim getClaimByClaimName(String claimName);
	
}
