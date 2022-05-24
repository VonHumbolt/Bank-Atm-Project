package com.kaankaplan.atm_project.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kaankaplan.atm_project.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

	@Query("From User u where u.acountNumber = :acountNumber")
	User getUserByAcoountNumber(String acountNumber);
}
