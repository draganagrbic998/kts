package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CulturalOfferRepositoryTest {
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	@Test
	public void test() {
		assertEquals(0, this.culturalOfferRepository.count());
	}

}
