package com.example.demo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.UserConstants;
import com.example.demo.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testFindByEmailExisting() {
		User u = this.userRepository.findByEmail(UserConstants.EMAIL_ONE);
		assertNotNull(u);
		assertEquals(UserConstants.ID_ONE, u.getId());
		assertEquals(UserConstants.EMAIL_ONE, u.getEmail());
		assertEquals(UserConstants.PASSWORD_ONE, u.getPassword());
		assertEquals(UserConstants.FIRST_NAME_ONE, u.getFirstName());
		assertEquals(UserConstants.LAST_NAME_ONE, u.getLastName());
	}
	
	@Test
	public void testFindByEmailNonExisting() {
		User u = this.userRepository.findByEmail(UserConstants.NON_EXISTING_EMAIL);
		assertNull(u);
	}
	
	@Test
	public void testHasEmailNewUserNonExisting() {
		User u = this.userRepository.hasEmail(null, UserConstants.NON_EXISTING_EMAIL);
		assertNull(u);
	}
	
	@Test
	public void testHasEmailNewUserExisting() {
		User u = this.userRepository.hasEmail(null, UserConstants.EMAIL_ONE);
		assertNotNull(u);
	}
	
	@Test
	public void testHasEmailOldUserOwnEmail() {
		User u = this.userRepository.hasEmail(UserConstants.ID_ONE, UserConstants.EMAIL_ONE);
		assertNull(u);
	}
	
	@Test
	public void testHasEmailOldUserNonExisting() {
		User u = this.userRepository.hasEmail(UserConstants.ID_ONE, UserConstants.NON_EXISTING_EMAIL);
		assertNull(u);
	}
	
	@Test
	public void testHasEmailOldUserExisting() {
		User u = this.userRepository.hasEmail(UserConstants.ID_ONE, UserConstants.EMAIL_TWO);
		assertNotNull(u);
	}

}
