package com.example.demo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.AccountActivationConstants;
import com.example.demo.constants.UserConstants;
import com.example.demo.model.AccountActivation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountActivationRepositoryTest {

	@Autowired
	private AccountActivationRepository accountActivationRepository;
	
	@Test
	public void testFindByCodeExisting() {
		AccountActivation a = this.accountActivationRepository.findByCode(AccountActivationConstants.CODE_ONE);
		assertNotNull(a);
		assertEquals(AccountActivationConstants.ID_ONE, a.getId());
		assertEquals(AccountActivationConstants.CODE_ONE, a.getCode());
		assertEquals(UserConstants.ID_ONE, a.getUser().getId());
		assertEquals(UserConstants.EMAIL_ONE, a.getUser().getEmail());
		assertEquals(UserConstants.PASSWORD_ONE, a.getUser().getPassword());
		assertEquals(UserConstants.FIRST_NAME_ONE, a.getUser().getFirstName());
		assertEquals(UserConstants.LAST_NAME_ONE, a.getUser().getLastName());
	}
	
	@Test
	public void testFindByCodeNonExisting() {
		AccountActivation a = this.accountActivationRepository.findByCode(AccountActivationConstants.NON_EXISTING_CODE);
		assertNull(a);
	}
	
}
