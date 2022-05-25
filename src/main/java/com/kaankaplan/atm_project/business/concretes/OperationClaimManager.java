package com.kaankaplan.atm_project.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaankaplan.atm_project.business.abstracts.OperationClaimService;
import com.kaankaplan.atm_project.dataAccess.OperationClaimDao;
import com.kaankaplan.atm_project.entity.OperationClaim;

@Service
public class OperationClaimManager implements OperationClaimService {

	private OperationClaimDao operationClaimDao;
	
	@Autowired
	public OperationClaimManager(OperationClaimDao operationClaimDao) {
		this.operationClaimDao = operationClaimDao;
	}
	
	@Override
	public OperationClaim getClaimByClaimName(String claimName) {
		
		return this.operationClaimDao.getClaimByClaimName(claimName);
	}
	
	@Override
	public OperationClaim getClaimByEmail(String email) {
		return this.operationClaimDao.getClaimByEmail(email);
	}

}
