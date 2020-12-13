package com.example.demo.service.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.CategoryConstants;
import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.FilterConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);
	private Pageable pageableNonExisting = PageRequest.of(1, 3);
	
	@Test
	public void testHasNameNewCategoryNonExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(CategoryConstants.NON_EXISTING_NAME);
		assertFalse(this.categoryService.hasName(param));
	}
	
	@Test
	public void testHasNameNewCategoryExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(CategoryConstants.NAME_ONE);
		assertTrue(this.categoryService.hasName(param));
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = this.categoryService.filterNames(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CategoryConstants.NAME_ONE));
		assertTrue(names.contains(CategoryConstants.NAME_TWO));
		assertTrue(names.contains(CategoryConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = this.categoryService.filterNames(CategoryConstants.FILTER_NAME_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CategoryConstants.NAME_ONE));
		assertTrue(names.contains(CategoryConstants.NAME_TWO));
		assertTrue(names.contains(CategoryConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = this.categoryService.filterNames(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(CategoryConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = this.categoryService.filterNames(FilterConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}
	
	@Test
	public void testListAll() {
		List<Category> categories = this.categoryService.list(this.pageableAll).getContent();
		assertEquals(MainConstants.TOTAL_SIZE, categories.size());
		assertEquals(CategoryConstants.ID_ONE, categories.get(0).getId());
		assertEquals(CategoryConstants.NAME_ONE, categories.get(0).getName());
		assertEquals(CategoryConstants.ID_TWO, categories.get(1).getId());
		assertEquals(CategoryConstants.NAME_TWO, categories.get(1).getName());
		assertEquals(CategoryConstants.ID_THREE, categories.get(2).getId());
		assertEquals(CategoryConstants.NAME_THREE, categories.get(2).getName());
	}
	
	@Test
	public void testListAllPaginated() {
		List<Category> categories = this.categoryService.list(this.pageablePart).getContent();
		assertEquals(MainConstants.PART_SIZE, categories.size());
		assertEquals(CategoryConstants.ID_ONE, categories.get(0).getId());
		assertEquals(CategoryConstants.NAME_ONE, categories.get(0).getName());
		assertEquals(CategoryConstants.ID_TWO, categories.get(1).getId());
		assertEquals(CategoryConstants.NAME_TWO, categories.get(1).getName());
	}
	
	@Test
	public void testListNonExistingPage() {
		List<Category> categories = this.categoryService.list(this.pageableNonExisting).getContent();
		assertEquals(MainConstants.NONE_SIZE, categories.size());
	}
	

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteExistingWithoutCulturalOffer() {
		Category cat = this.testingCategory();
		this.categoryRepository.save(cat);
		long size = this.categoryRepository.count();
		this.categoryService.delete(CategoryConstants.ID_FOUR);
		assertEquals(size - 1, this.categoryRepository.count());
		assertNull(this.categoryRepository.findById(CategoryConstants.ID_FOUR).orElse(null));
	}
	
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testDeleteExistingWithCulturalOffer() {
		this.categoryService.delete(CategoryConstants.ID_ONE);
		this.categoryRepository.count();
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	@Transactional
	@Rollback(true)
	public void testDeleteNonExisting() {
		this.categoryService.delete(MainConstants.NON_EXISTING_ID);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveValid() {
		long size = this.categoryRepository.count();
		Category category = this.testingCategory();
		this.categoryService.save(category);
		assertEquals(size + 1, this.categoryRepository.count());
		assertEquals(CategoryConstants.NON_EXISTING_NAME, category.getName());
	}
	
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullName() {
		Category category = this.testingCategory();
		category.setName(null);
		this.categoryService.save(category);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyName() {
		Category category = this.testingCategory();
		category.setName("");
		this.categoryService.save(category);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankName() {
		Category category = this.testingCategory();
		category.setName("  ");
		this.categoryService.save(category);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNonUniqueName() {
		Category category = this.testingCategory();
		category.setName(CategoryConstants.NAME_ONE);
		this.categoryService.save(category);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		long size = this.categoryRepository.count();
		Category category = this.testingCategory();
		category.setId(CulturalOfferConstants.ID_ONE);
		this.categoryRepository.save(category);
		assertEquals(size, this.categoryRepository.count());
		assertEquals(CategoryConstants.ID_ONE, category.getId());
		assertEquals(CategoryConstants.NON_EXISTING_NAME, category.getName());
	}
	
	public Category testingCategory() {
		Category category = new Category();
		category.setName(CategoryConstants.NON_EXISTING_NAME);
		return category;
	}
	
	
	

}
