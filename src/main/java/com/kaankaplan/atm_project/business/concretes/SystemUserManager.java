package com.kaankaplan.atm_project.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaankaplan.atm_project.business.abstracts.SystemUserService;
import com.kaankaplan.atm_project.dataAccess.SystemUserDao;
import com.kaankaplan.atm_project.entity.SystemUser;

@Service
public class SystemUserManager implements SystemUserService {

	private SystemUserDao systemUserDao;
	
	@Autowired
	public SystemUserManager(SystemUserDao systemUserDao) {
		this.systemUserDao = systemUserDao;
	}

	@Override
	public SystemUser getSystemUserByEmail(String email) {
		
		return this.systemUserDao.getSystemUserByEmail(email);
	}
}
