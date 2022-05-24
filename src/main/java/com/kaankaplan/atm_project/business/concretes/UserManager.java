package com.kaankaplan.atm_project.business.concretes;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kaankaplan.atm_project.business.abstracts.OperationClaimService;
import com.kaankaplan.atm_project.business.abstracts.UserService;
import com.kaankaplan.atm_project.dataAccess.UserDao;
import com.kaankaplan.atm_project.entity.OperationClaim;
import com.kaankaplan.atm_project.entity.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserManager implements UserService{

	private UserDao userDao;
	private OperationClaimService operationClaimService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserManager(UserDao userDao, OperationClaimService operationClaimService, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.operationClaimService = operationClaimService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public List<User> getallUsers(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		log.info("All users.....");
		return this.userDao.findAll(pageable).getContent();
	}

	@Override
	public User getByUserId(int userId) {
		log.info("GetById....." + this.userDao.getById(userId).toString());
		
		return this.userDao.getById(userId);
	}

	@Override
	public User getByAccountNumber(String accountNumber) {
		log.info("GetByAccountNumber -> ");
		
		return this.userDao.getUserByAcoountNumber(accountNumber);
	}
	
	@Override
	public void add(User user) {
		log.info("-> User eklendi");
		
		OperationClaim claim = this.operationClaimService.getClaimByClaimName("USER");
		user.setOperationClaim(claim);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		this.userDao.save(user);
		
	}

	@Override
	public void delete(int userId) {
		
		this.userDao.deleteById(userId);
	}

	@Override
	public void update(User user) {
		
		User userFromDb = this.userDao.getById(user.getUserId());
		userFromDb.setEmail(user.getEmail());
		userFromDb.setFirstName(user.getFirstName());
		userFromDb.setLastName(user.getLastName());
		userFromDb.setMoney(user.getMoney());
		userFromDb.setPassword(user.getPassword());
		
		this.userDao.save(userFromDb);
	}

	@Transactional
	@Override
	public void withdrawMoney(String accountNumber, double money) {
		
		User user = this.userDao.getUserByAcoountNumber(accountNumber);
		
		if(user.getMoney() > 0) {
			System.out.println("Para çekme işlemi gerçekleşiyor...");
			user.setMoney(user.getMoney() - money);
			this.userDao.save(user);
			System.out.println("İşlem başarılı...");
		} else {
			System.out.println("Yeterli bakiyeniz bulunmamaktadır.");
		}
		
		log.info("WithDrawMoney -> ");
		
	}

	@Transactional
	@Override
	public void loadMoney(String accountNumber, double money) {
		
		User user = this.userDao.getUserByAcoountNumber(accountNumber);
		System.out.println("Para yükleme işlemi gerçekleşiyor...");
		user.setMoney(user.getMoney() + money);
		this.userDao.save(user);
		System.out.println("İşlem başarılı...");
		
		log.info("LoadMoney -> ");
	}

	@Transactional
	@Override
	public void transferMoney(String fromAccountNumber, String toAccountNumber, double money) {
		
		log.info("TransferMoney -> ");
		
		User fromUser = this.userDao.getUserByAcoountNumber(fromAccountNumber);
		User toUser = this.userDao.getUserByAcoountNumber(toAccountNumber);
		
		if (fromUser.getMoney() <= 0) {
			System.out.println("Yeterli bakiyeniz bulunmamaktadır...");
			
		} else {
			fromUser.setMoney(fromUser.getMoney() - money);
			toUser.setMoney(toUser.getMoney() + money);
			
			this.userDao.save(fromUser);
			this.userDao.save(toUser);
			
			System.out.println("İşlem başarılı...");
		}
		
	}

	

}
