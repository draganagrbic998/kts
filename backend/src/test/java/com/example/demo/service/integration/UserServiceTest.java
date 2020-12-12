package com.example.demo.service.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.UserConstants;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.AuthToken;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Test
	public void testloadUserByUsernameExisting() {
		User u = (User) this.userService.loadUserByUsername(UserConstants.EMAIL_ONE);
		assertNotNull(u);
		assertEquals(UserConstants.ID_ONE, u.getId());
		assertEquals(UserConstants.EMAIL_ONE, u.getEmail());
		assertEquals(UserConstants.PASSWORD_ONE, u.getPassword());
		assertEquals(UserConstants.FIRST_NAME_ONE, u.getFirstName());
		assertEquals(UserConstants.LAST_NAME_ONE, u.getLastName());
	}
	
	@Test
	public void testloadUserByUsernameNonExisting() {
		User u = (User) this.userService.loadUserByUsername(UserConstants.NON_EXISTING_EMAIL);
		assertNull(u);
	}
	
	@Test
	public void testHasEmailNewUserNonExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(UserConstants.NON_EXISTING_EMAIL);
		assertFalse(this.userService.hasEmail(param));
	}
	
	@Test
	public void testHasEmailNewUserExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(UserConstants.EMAIL_ONE);
		assertTrue(this.userService.hasEmail(param));
	}
	
	@Test
	public void testHasEmailOldUserOwnEmail() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(UserConstants.ID_ONE);
		param.setName(UserConstants.EMAIL_ONE);
		assertFalse(this.userService.hasEmail(param));
	}
	
	@Test
	public void testHasEmailOldUserNonExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(UserConstants.ID_ONE);
		param.setName(UserConstants.NON_EXISTING_EMAIL);
		assertFalse(this.userService.hasEmail(param));
	}
	
	@Test
	public void testHasEmailOldUserExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(UserConstants.ID_ONE);
		param.setName(UserConstants.EMAIL_TWO);
		assertTrue(this.userService.hasEmail(param));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveValid() {
		long size = this.userRepository.count();
		User u = this.testingUser();
		u = this.userService.save(u, null);
		assertEquals(size + 1, this.userRepository.count());
		assertEquals(UserConstants.NON_EXISTING_EMAIL, u.getEmail());
		assertEquals(UserConstants.PASSWORD_ONE, u.getPassword());
		assertEquals(UserConstants.FIRST_NAME_ONE, u.getFirstName());
		assertEquals(UserConstants.LAST_NAME_ONE, u.getLastName());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullEmail() {
		User u = this.testingUser();
		u.setEmail(null);
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyEmail() {
		User u = this.testingUser();
		u.setEmail("");
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankEmail() {
		User u = this.testingUser();
		u.setEmail("  ");
		this.userService.save(u, null);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNonUniqueEmail() {
		User u = this.testingUser();
		u.setEmail(UserConstants.EMAIL_ONE);
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullPassword() {
		User u = this.testingUser();
		u.setPassword(null);
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyPassword() {
		User u = this.testingUser();
		u.setPassword("");
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankPassword() {
		User u = this.testingUser();
		u.setPassword(" ");
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullFirstName() {
		User u = this.testingUser();
		u.setFirstName(null);
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyFirstName() {
		User u = this.testingUser();
		u.setFirstName("");
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankFirstName() {
		User u = this.testingUser();
		u.setFirstName("  ");
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullLastName() {
		User u = this.testingUser();
		u.setLastName(null);
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyLastName() {
		User u = this.testingUser();
		u.setLastName("");
		this.userService.save(u, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlanklLastName() {
		User u = this.testingUser();
		u.setLastName("  ");
		this.userService.save(u, null);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		long size = this.userRepository.count();
		User u = this.testingUser();
		u.setId(UserConstants.ID_ONE);
		u = this.userService.save(u, null);
		assertEquals(size, this.userRepository.count());
		assertEquals(UserConstants.ID_ONE, u.getId());
		assertEquals(UserConstants.NON_EXISTING_EMAIL, u.getEmail());
		assertEquals(UserConstants.PASSWORD_ONE, u.getPassword());
		assertEquals(UserConstants.FIRST_NAME_ONE, u.getFirstName());
		assertEquals(UserConstants.LAST_NAME_ONE, u.getLastName());
	}
	
	@Test
	public void testCurrentUserNull() {
		this.removeAuthentication();
		assertNull(this.userService.currentUser());
	}
	
	@Test
	public void testCurrentUserNotNull() {
		this.setAuthentication(false);
		User u = this.userService.currentUser();
		assertNotNull(u);
		assertEquals(UserConstants.ID_TWO, u.getId());
		assertEquals(UserConstants.EMAIL_TWO, u.getEmail());
		assertEquals(UserConstants.PASSWORD_TWO, u.getPassword());
		assertEquals(UserConstants.FIRST_NAME_TWO, u.getFirstName());
		assertEquals(UserConstants.LAST_NAME_TWO, u.getLastName());
	}
	
	@Test
	public void testUserIsFollowingNullUser() {
		this.removeAuthentication();
		assertFalse(this.userService.userIsFollowing(CulturalOfferConstants.ID_ONE));
	}
	
	@Test
	public void testUserIsFollowingAdminUser() {
		this.setAuthentication(true);
		assertFalse(this.userService.userIsFollowing(CulturalOfferConstants.ID_ONE));
	}
	
	@Test
	public void testUserIsFollowingNonExisting() {
		this.setAuthentication(false);
		assertFalse(this.userService.userIsFollowing(CulturalOfferConstants.ID_TWO));
	}
	
	@Test
	public void testUserIsFollowingExisting() {
		this.setAuthentication(false);
		assertTrue(this.userService.userIsFollowing(CulturalOfferConstants.ID_ONE));
	}
	
	public User testingUser() {
		User u = new User();
		u.setEmail(UserConstants.NON_EXISTING_EMAIL);
		u.setPassword(UserConstants.PASSWORD_ONE);
		u.setFirstName(UserConstants.FIRST_NAME_ONE);
		u.setLastName(UserConstants.LAST_NAME_ONE);
		return u;
	}
	
	public void setAuthentication(boolean admin) {
		User user = this.userRepository.findById(UserConstants.ID_TWO).orElse(null);
		if (admin) {
			user.getAuthority().setName("admin");
		}
		String token = this.tokenUtils.generateToken(user.getUsername());
		AuthToken authToken = new AuthToken(user, token);
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}
	
	public void removeAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
}
