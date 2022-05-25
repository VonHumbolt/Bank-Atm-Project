package com.kaankaplan.atm_project.business.abstracts;

import com.kaankaplan.atm_project.entity.SystemUser;

public interface SystemUserService {
	
	SystemUser getSystemUserByEmail(String email);
}
