package com.example.demo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.AuthorityConstants;
import com.example.demo.model.Authority;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityRepositoryTest {
		
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Test
	public void testFindByExistingName() {
		Authority a = this.authorityRepository.findByName(AuthorityConstants.NAME_ONE);
		assertNotNull(a);
		assertEquals(AuthorityConstants.ID_ONE, a.getId());
		assertEquals(AuthorityConstants.NAME_ONE, a.getName());
	}

	@Test
	public void testFindByNonExistingName() {
		Authority a = this.authorityRepository.findByName(AuthorityConstants.NON_EXISTING_NAME);
		assertNull(a);
	}
	
}
