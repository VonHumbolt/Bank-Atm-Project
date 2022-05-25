package com.kaankaplan.atm_project.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kaankaplan.atm_project.entity.SystemUser;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer>{

	@Query("From SystemUser s where s.email = :email")
	SystemUser getSystemUserByEmail(String email);
}
