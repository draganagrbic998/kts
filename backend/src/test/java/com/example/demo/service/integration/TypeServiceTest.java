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
import com.example.demo.constants.FilterConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.TypeConstants;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.Type;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.TypeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeServiceTest {
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);
	private Pageable pageableNonExisting = PageRequest.of(1, 3);

	@Test
	public void testHasNameNewTypeNonExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(TypeConstants.NON_EXISTING_NAME);
		assertFalse(this.typeService.hasName(param));
	}
	
	@Test
	public void testHasNameNewTypeExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(TypeConstants.NAME_ONE);
		assertTrue(this.typeService.hasName(param));
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = this.typeService.filterNames(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(TypeConstants.NAME_ONE));
		assertTrue(names.contains(TypeConstants.NAME_TWO));
		assertTrue(names.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = this.typeService.filterNames(TypeConstants.FILTER_NAME_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(TypeConstants.NAME_ONE));
		assertTrue(names.contains(TypeConstants.NAME_TWO));
		assertTrue(names.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = this.typeService.filterNames(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(TypeConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = this.typeService.filterNames(FilterConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}
	
	@Test
	public void testListAll() {
		List<Type> types = this.typeService.list(this.pageableAll).getContent();
		assertEquals(MainConstants.TOTAL_SIZE, types.size());
		assertEquals(TypeConstants.ID_ONE, types.get(0).getId());
		assertEquals(TypeConstants.NAME_ONE, types.get(0).getName());
		assertEquals(TypeConstants.PLACEMARK_ONE, types.get(0).getPlacemarkIcon());
		assertEquals(TypeConstants.ID_TWO, types.get(1).getId());
		assertEquals(TypeConstants.NAME_TWO, types.get(1).getName());
		assertEquals(TypeConstants.PLACEMARK_TWO, types.get(1).getPlacemarkIcon());
		assertEquals(TypeConstants.ID_THREE, types.get(2).getId());
		assertEquals(TypeConstants.NAME_THREE, types.get(2).getName());
		assertEquals(TypeConstants.PLACEMARK_THREE, types.get(2).getPlacemarkIcon());
	}
	
	@Test
	public void testListAllPaginated() {
		List<Type> types = this.typeService.list(this.pageablePart).getContent();
		assertEquals(MainConstants.PART_SIZE, types.size());
		assertEquals(TypeConstants.ID_ONE, types.get(0).getId());
		assertEquals(TypeConstants.NAME_ONE, types.get(0).getName());
		assertEquals(TypeConstants.PLACEMARK_ONE, types.get(0).getPlacemarkIcon());
		assertEquals(TypeConstants.ID_TWO, types.get(1).getId());
		assertEquals(TypeConstants.NAME_TWO, types.get(1).getName());
		assertEquals(TypeConstants.PLACEMARK_TWO, types.get(1).getPlacemarkIcon());
	}
	
	@Test
	public void testListNonExistingPage() {
		List<Type> types  = this.typeService.list(this.pageableNonExisting).getContent();
		assertEquals(MainConstants.NONE_SIZE, types.size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteExistingWithoutCulturalOffer() {
		Type type = this.testingType();
		this.typeRepository.save(type);
		long size = this.typeRepository.count();
		this.typeService.delete(TypeConstants.ID_FOUR);
		assertEquals(size - 1, this.typeRepository.count());
		assertNull(this.typeRepository.findById(TypeConstants.ID_FOUR).orElse(null));
	}
	
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testDeleteExistingWithCulturalOffer() {
		this.typeService.delete(CategoryConstants.ID_ONE);
		this.typeRepository.count();
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	@Transactional
	@Rollback(true)
	public void testDeleteNonExisting() {
		this.typeService.delete(MainConstants.NON_EXISTING_ID);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSaveValid() {
		long size = this.typeRepository.count();
		Type type = this.testingType();
		this.typeService.save(type,null);
		assertEquals(size + 1, this.typeRepository.count());
		assertEquals(TypeConstants.NON_EXISTING_NAME, type.getName());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullCategory() {
		Type type = this.testingType();
		type.setCategory(null);
		this.typeService.save(type, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullName() {
		Type type = this.testingType();
		type.setName(null);
		this.typeService.save(type,null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyName() {
		Type type = this.testingType();
		type.setName("");
		this.typeService.save(type,null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankName() {
		Type type = this.testingType();
		type.setName("  ");
		this.typeService.save(type,null);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNonUniqueName() {
		Type type = this.testingType();
		type.setName(TypeConstants.NAME_ONE);
		this.typeService.save(type,null);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		long size = this.typeRepository.count();
		Type type = this.testingType();
		type.setId(TypeConstants.ID_ONE);
		this.typeService.save(type,null);
		assertEquals(size, this.typeRepository.count());
		assertEquals(TypeConstants.ID_ONE, type.getId());
		assertEquals(TypeConstants.NON_EXISTING_NAME, type.getName());
		assertEquals(TypeConstants.PLACEMARK_THREE, type.getPlacemarkIcon());

	}
	
	public Type testingType() {
		Type type = new Type();
		type.setName(TypeConstants.NON_EXISTING_NAME);
		type.setCategory(this.categoryRepository.findById(1l).orElse(null));
		type.setPlacemarkIcon(TypeConstants.PLACEMARK_THREE);
		return type;
	}
}
