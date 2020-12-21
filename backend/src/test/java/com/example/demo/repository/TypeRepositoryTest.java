package com.example.demo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.CategoryConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.TypeConstants;
import com.example.demo.model.Type;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeRepositoryTest {
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Test
	public void testFindByNameExisting() {
		Type type = 
				this.typeRepository
				.findByName(TypeConstants.NAME_ONE);
		assertNotNull(type);
		assertEquals(TypeConstants.ID_ONE, type.getId());
		assertEquals(CategoryConstants.ID_ONE, type.getCategory().getId());
		assertEquals(TypeConstants.NAME_ONE, type.getName());
	}

	@Test
	public void testFindByNameNonExisting() {
		Type type = 
				this.typeRepository
				.findByName(TypeConstants.NON_EXISTING_NAME);
		assertNull(type);
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = 
				this.typeRepository
				.filterNames(MainConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertEquals(TypeConstants.NAME_ONE, names.get(0));
		assertEquals(TypeConstants.NAME_THREE, names.get(1));
		assertEquals(TypeConstants.NAME_TWO, names.get(2));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = 
				this.typeRepository
				.filterNames(TypeConstants.FILTER_NAMES_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertEquals(TypeConstants.NAME_ONE, names.get(0));
		assertEquals(TypeConstants.NAME_THREE, names.get(1));
		assertEquals(TypeConstants.NAME_TWO, names.get(2));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = 
				this.typeRepository
				.filterNames(MainConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(TypeConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = 
				this.typeRepository
				.filterNames(MainConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}
	
}
