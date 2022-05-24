package com.kaankaplan.atm_project.business.concretes;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaankaplan.atm_project.business.abstracts.AdminService;
import com.kaankaplan.atm_project.business.abstracts.OperationClaimService;
import com.kaankaplan.atm_project.dataAccess.AdminDao;
import com.kaankaplan.atm_project.entity.Admin;
import com.kaankaplan.atm_project.entity.OperationClaim;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminManager implements AdminService {

	private AdminDao adminDao;
	private OperationClaimService operationClaimService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminManager(AdminDao adminDao, OperationClaimService operationClaimService, PasswordEncoder passwordEncoder) {
		this.adminDao = adminDao;
		this.operationClaimService = operationClaimService;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	@Override
	public void add(Admin admin) {
		log.info("Admin eklendi -> " + admin.toString());
		
		OperationClaim claim = this.operationClaimService.getClaimByClaimName("ADMIN");
		admin.setOperationClaim(claim);
		
		this.adminDao.save(admin);
	}

	@Transactional
	@Override
	public void delete(int adminId) {
		
		log.info("Admin silindi -> ");
		
		this.adminDao.deleteById(adminId);
		
	}

	@Transactional
	@Override
	public void update(Admin admin) {
		
		log.info("Admin güncellendi -> ");
		
		Admin adminFromDb = this.adminDao.getById(admin.getAdminId());
		
		adminFromDb.setBankName(admin.getBankName());
		adminFromDb.setEmail(admin.getEmail());
		adminFromDb.setPassword(admin.getPassword());
		
		this.adminDao.save(adminFromDb);
	}
}
