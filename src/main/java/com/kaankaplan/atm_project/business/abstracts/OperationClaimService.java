package com.kaankaplan.atm_project.business.abstracts;

import com.kaankaplan.atm_project.entity.OperationClaim;

public interface OperationClaimService {
	
	OperationClaim getClaimByClaimName(String claimName);
}
