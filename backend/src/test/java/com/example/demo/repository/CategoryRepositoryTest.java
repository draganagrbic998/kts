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
import com.example.demo.model.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testFindByNameExisting() {
		Category category = 
				this.categoryRepository
				.findByName(CategoryConstants.NAME_ONE);
		assertNotNull(category);
		assertEquals(CategoryConstants.ID_ONE, category.getId());
		assertEquals(CategoryConstants.NAME_ONE, category.getName());
	}

	@Test
	public void testFindByNameNonExisting() {
		Category category = 
				this.categoryRepository
				.findByName(CategoryConstants.NON_EXISTING_NAME);
		assertNull(category);
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = 
				this.categoryRepository
				.filterNames(MainConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertEquals(CategoryConstants.NAME_ONE, names.get(0));
		assertEquals(CategoryConstants.NAME_THREE, names.get(1));
		assertEquals(CategoryConstants.NAME_TWO, names.get(2));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = 
				this.categoryRepository
				.filterNames(CategoryConstants.FILTER_NAMES_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertEquals(CategoryConstants.NAME_ONE, names.get(0));
		assertEquals(CategoryConstants.NAME_THREE, names.get(1));
		assertEquals(CategoryConstants.NAME_TWO, names.get(2));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = 
				this.categoryRepository
				.filterNames(MainConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(CategoryConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = 
				this.categoryRepository
				.filterNames(MainConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}

}
