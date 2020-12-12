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
import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.FilterConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.model.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testFindByExistingName() {
		Category c = this.categoryRepository.findByName(CategoryConstants.NAME_ONE);
		assertNotNull(c);
		assertEquals(CategoryConstants.ID_ONE, c.getId());
		assertEquals(CategoryConstants.NAME_ONE, c.getName());
	}

	@Test
	public void testFindByNonExistingName() {
		Category c = this.categoryRepository.findByName(CategoryConstants.NON_EXISTING_NAME);
		assertNull(c);
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = this.categoryRepository.filterNames(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CategoryConstants.NAME_ONE));
		assertTrue(names.contains(CategoryConstants.NAME_TWO));
		assertTrue(names.contains(CategoryConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = this.categoryRepository.filterNames(CategoryConstants.FILTER_NAME_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CategoryConstants.NAME_ONE));
		assertTrue(names.contains(CategoryConstants.NAME_TWO));
		assertTrue(names.contains(CategoryConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = this.categoryRepository.filterNames(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(CategoryConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = this.categoryRepository.filterNames(FilterConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}

}
