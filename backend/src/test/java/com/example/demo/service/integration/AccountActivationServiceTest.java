package com.example.demo.service.integration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.AccountActivationConstants;
import com.example.demo.constants.UserConstants;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountActivationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountActivationServiceTest {
	
	@Autowired
	private AccountActivationService accountActivationService;
	
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

}
