package com.kaankaplan.atm_project.entity.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

	private String email;
	private String password;
}
