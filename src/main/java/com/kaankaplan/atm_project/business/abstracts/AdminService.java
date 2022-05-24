package com.kaankaplan.atm_project.business.abstracts;

import com.kaankaplan.atm_project.entity.Admin;

public interface AdminService {

	void add(Admin admin);
	
	void delete(int adminId);
	
	void update(Admin admin);
}
