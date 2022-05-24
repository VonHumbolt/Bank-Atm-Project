package com.kaankaplan.atm_project.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaankaplan.atm_project.entity.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{

}
