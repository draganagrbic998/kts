package com.example.demo.service.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.example.demo.constants.AccountActivationConstants;
import com.example.demo.constants.UserConstants;
import com.example.demo.model.User;
import com.example.demo.repository.AccountActivationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountActivationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountActivationServiceTest {
	
	@Autowired
	private AccountActivationService accountActivationService;
	
	@Autowired
	private AccountActivationRepository accountActivationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testActivateExisting() {
		this.accountActivationService.activate(AccountActivationConstants.CODE_ONE);
		assertTrue(this.userRepository.findById(UserConstants.ID_ONE).orElse(null).isEnabled());
	}
	
	@Test(expected = NullPointerException.class)
	@Transactional
	@Rollback(true)
	public void testActivateNonExisting() {
		this.accountActivationService.activate(AccountActivationConstants.NON_EXISTING_CODE);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveUserExisting() {
		long size = this.accountActivationRepository.count();
		User u = this.userRepository.findByEmail(UserConstants.EMAIL_ONE);
		System.out.println(u.getId());
		this.accountActivationService.save(u);
		assertEquals(size + 1,this.accountActivationRepository.count());
	}
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	@Transactional
	@Rollback(true)
	public void testSaveUserNonExisting() {
		User u = this.testingUser();
		this.accountActivationService.save(u);
	}
	
	public User testingUser() {
		User u = new User();
		u.setEmail(UserConstants.NON_EXISTING_EMAIL);
		u.setFirstName(UserConstants.FIRST_NAME_ONE);
		u.setLastName(UserConstants.LAST_NAME_ONE);
		u.setPassword(UserConstants.PASSWORD_ONE);
		return u;
	}

}
