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

import com.example.demo.constants.FilterConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.TypeConstants;
import com.example.demo.model.Type;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeRepositoryTest {
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Test
	public void testFindByExistingName() {
		Type t = this.typeRepository.findByName(TypeConstants.NAME_ONE);
		assertNotNull(t);
		assertEquals(TypeConstants.ID_ONE, t.getId());
		assertEquals(TypeConstants.NAME_ONE, t.getName());
	}

	@Test
	public void testFindByNonExistingName() {
		Type t = this.typeRepository.findByName(TypeConstants.NON_EXISTING_NAME);
		assertNull(t);
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = this.typeRepository.filterNames(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(TypeConstants.NAME_ONE));
		assertTrue(names.contains(TypeConstants.NAME_TWO));
		assertTrue(names.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = this.typeRepository.filterNames(TypeConstants.FILTER_NAME_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(TypeConstants.NAME_ONE));
		assertTrue(names.contains(TypeConstants.NAME_TWO));
		assertTrue(names.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = this.typeRepository.filterNames(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(TypeConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = this.typeRepository.filterNames(FilterConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}
	
}
