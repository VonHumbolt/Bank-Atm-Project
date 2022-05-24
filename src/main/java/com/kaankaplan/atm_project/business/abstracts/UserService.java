package com.kaankaplan.atm_project.business.abstracts;

import java.util.List;

import com.kaankaplan.atm_project.entity.User;

public interface UserService {
	
	List<User> getallUsers(int pageNo, int pageSize);
	
	User getByUserId(int userId);
	
	User getByAccountNumber(String accountNumber);
	
	void add(User user);
	
	void delete(int userId);
	
	void update(User user);
	
	void withdrawMoney(String accountNumber, double money);
	
	void loadMoney(String accountNumber, double money);
	
	void transferMoney(String fromAccountNumber, String toAccountNumber, double money);
}
