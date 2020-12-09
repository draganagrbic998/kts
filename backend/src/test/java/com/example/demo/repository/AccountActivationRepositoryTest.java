package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountActivationRepositoryTest {

	@Autowired
	private AccountActivationRepository accountActivationRepository;
	
	@Test
	public void test() {
		assertEquals(this.accountActivationRepository.count(), 0);
	}
	
}
