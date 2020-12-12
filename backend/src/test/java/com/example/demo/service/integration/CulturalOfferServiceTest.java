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

import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.FilterConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.OfferFilters;
import com.example.demo.constants.TypeConstants;
import com.example.demo.dto.FilterParamsDTO;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CulturalOfferRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.CulturalOfferService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CulturalOfferServiceTest {

	@Autowired
	private CulturalOfferService culturalOfferService;
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private OfferFilters filters;
		
	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);
	
	@Test
	public void testHasNameNewOfferNonExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(CulturalOfferConstants.NON_EXISTING_NAME);
		assertFalse(this.culturalOfferService.hasName(param));
	}
	
	@Test
	public void testHasNameNewOfferExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(null);
		param.setName(CulturalOfferConstants.NAME_ONE);
		assertTrue(this.culturalOfferService.hasName(param));
	}
	
	@Test
	public void testHasNameOldOfferOwnName() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(CulturalOfferConstants.ID_ONE);
		param.setName(CulturalOfferConstants.NAME_ONE);
		assertFalse(this.culturalOfferService.hasName(param));
	}
	
	@Test
	public void testHasNameOldOfferNonExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(CulturalOfferConstants.ID_ONE);
		param.setName(CulturalOfferConstants.NON_EXISTING_NAME);
		assertFalse(this.culturalOfferService.hasName(param));
	}
	
	@Test
	public void testHasNameOldOfferExisting() {
		UniqueCheckDTO param = new UniqueCheckDTO();
		param.setId(CulturalOfferConstants.ID_ONE);
		param.setName(CulturalOfferConstants.NAME_TWO);
		assertTrue(this.culturalOfferService.hasName(param));
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = this.culturalOfferService.filterNames(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CulturalOfferConstants.NAME_ONE));
		assertTrue(names.contains(CulturalOfferConstants.NAME_TWO));
		assertTrue(names.contains(CulturalOfferConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = this.culturalOfferService.filterNames(CulturalOfferConstants.FILTER_NAME_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CulturalOfferConstants.NAME_ONE));
		assertTrue(names.contains(CulturalOfferConstants.NAME_TWO));
		assertTrue(names.contains(CulturalOfferConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = this.culturalOfferService.filterNames(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(CulturalOfferConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = this.culturalOfferService.filterNames(FilterConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}
	
	@Test
	public void testFilterLocationsEmpty() {
		List<String> locations = this.culturalOfferService.filterLocations(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, locations.size());
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_ONE));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_TWO));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_THREE));
	}
	
	@Test
	public void testFilterLocationsAll() {
		List<String> locations = this.culturalOfferService.filterLocations(CulturalOfferConstants.FILTER_LOCATION_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, locations.size());
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_ONE));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_TWO));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_THREE));
	}
	
	@Test
	public void testFilterLocationsOne() {
		List<String> locations = this.culturalOfferService.filterLocations(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, locations.size());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, locations.get(0));
	}
	
	@Test
	public void testFilterLocationsNone() {
		List<String> locations = this.culturalOfferService.filterLocations(FilterConstants.FILTER_NONE);
		assertTrue(locations.isEmpty());
	}
	
	@Test
	public void testFilterTypesEmpty() {
		List<String> types = this.culturalOfferService.filterTypes(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, types.size());
		assertTrue(types.contains(TypeConstants.NAME_ONE));
		assertTrue(types.contains(TypeConstants.NAME_TWO));
		assertTrue(types.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterTypesAll() {
		List<String> types = this.culturalOfferService.filterTypes(CulturalOfferConstants.FILTER_TYPE_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, types.size());
		assertTrue(types.contains(TypeConstants.NAME_ONE));
		assertTrue(types.contains(TypeConstants.NAME_TWO));
		assertTrue(types.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterTypesOne() {
		List<String> types = this.culturalOfferService.filterTypes(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, types.size());
		assertEquals(TypeConstants.NAME_ONE, types.get(0));
	}
	
	@Test
	public void testFilterTypesNone() {
		List<String> types = this.culturalOfferService.filterTypes(FilterConstants.FILTER_NONE);
		assertTrue(types.isEmpty());
	}
		
	@Test
	public void testFilterEmpty() {
		FilterParamsDTO filters = this.filters.filtersEmpty();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageableAll).getContent();
		assertEquals(MainConstants.TOTAL_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
		assertEquals(CulturalOfferConstants.ID_THREE, offers.get(1).getId());
		assertEquals(CulturalOfferConstants.NAME_THREE, offers.get(1).getName());
		assertEquals(CulturalOfferConstants.LOCATION_THREE, offers.get(1).getLocation());
		assertEquals(CulturalOfferConstants.ID_TWO, offers.get(2).getId());
		assertEquals(CulturalOfferConstants.NAME_TWO, offers.get(2).getName());
		assertEquals(CulturalOfferConstants.LOCATION_TWO, offers.get(2).getLocation());
	}
	
	@Test
	public void testFilterEmptyPaginated() {
		FilterParamsDTO filters = this.filters.filtersEmpty();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageablePart).getContent();
		assertEquals(MainConstants.PART_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
		assertEquals(CulturalOfferConstants.ID_THREE, offers.get(1).getId());
		assertEquals(CulturalOfferConstants.NAME_THREE, offers.get(1).getName());
		assertEquals(CulturalOfferConstants.LOCATION_THREE, offers.get(1).getLocation());
	}
	
	@Test
	public void testFilterAll() {
		FilterParamsDTO filters = this.filters.filtersAll();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageableAll).getContent();
		assertEquals(MainConstants.TOTAL_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
		assertEquals(CulturalOfferConstants.ID_THREE, offers.get(1).getId());
		assertEquals(CulturalOfferConstants.NAME_THREE, offers.get(1).getName());
		assertEquals(CulturalOfferConstants.LOCATION_THREE, offers.get(1).getLocation());
		assertEquals(CulturalOfferConstants.ID_TWO, offers.get(2).getId());
		assertEquals(CulturalOfferConstants.NAME_TWO, offers.get(2).getName());
		assertEquals(CulturalOfferConstants.LOCATION_TWO, offers.get(2).getLocation());
	}
	
	@Test
	public void testFilterAllPaginated() {
		FilterParamsDTO filters = this.filters.filtersAll();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageablePart).getContent();
		assertEquals(MainConstants.PART_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
		assertEquals(CulturalOfferConstants.ID_THREE, offers.get(1).getId());
		assertEquals(CulturalOfferConstants.NAME_THREE, offers.get(1).getName());
		assertEquals(CulturalOfferConstants.LOCATION_THREE, offers.get(1).getLocation());
	}
	
	@Test
	public void testFilterOneName() {
		FilterParamsDTO filters = this.filters.filtersOneName();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneLocation() {
		FilterParamsDTO filters = this.filters.filtersOneLocation();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneType() {
		FilterParamsDTO filters = this.filters.filtersOneType();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneNameLocationType() {
		FilterParamsDTO filters = this.filters.filtersOne();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterNone() {
		FilterParamsDTO filters = this.filters.filtersNone();
		List<CulturalOffer> offers = this.culturalOfferService.filter(filters, this.pageableAll).getContent();
		assertTrue(offers.isEmpty());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteExisting() {
		long size = this.culturalOfferRepository.count();
		this.culturalOfferService.delete(CulturalOfferConstants.ID_ONE);
		assertEquals(size - 1, this.culturalOfferRepository.count());
		assertNull(this.culturalOfferRepository.findById(CulturalOfferConstants.ID_ONE).orElse(null));
		assertTrue(this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(CulturalOfferConstants.ID_ONE, this.pageableAll).isEmpty());
		//treba petar da doda da su i novosti obrisane
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	@Transactional
	@Rollback(true)
	public void testDeleteNonExisting() {
		this.culturalOfferService.delete(MainConstants.NON_EXISTING_ID);
	}
		
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveValid() {
		long size = this.culturalOfferRepository.count();
		CulturalOffer offer = this.testingOffer();
		offer = this.culturalOfferService.save(offer, null);
		assertEquals(size + 1, this.culturalOfferRepository.count());
		assertEquals(CulturalOfferConstants.NON_EXISTING_NAME, offer.getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offer.getLocation());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullType() {
		CulturalOffer offer = this.testingOffer();
		offer.setType(null);
		this.culturalOfferService.save(offer, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullName() {
		CulturalOffer offer = this.testingOffer();
		offer.setName(null);
		this.culturalOfferService.save(offer, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyName() {
		CulturalOffer offer = this.testingOffer();
		offer.setName("");
		this.culturalOfferService.save(offer, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankName() {
		CulturalOffer offer = this.testingOffer();
		offer.setName("  ");
		this.culturalOfferService.save(offer, null);
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNonUniqueName() {
		CulturalOffer offer = this.testingOffer();
		offer.setName(CulturalOfferConstants.NAME_ONE);
		this.culturalOfferService.save(offer, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullLocation() {
		CulturalOffer offer = this.testingOffer();
		offer.setLocation(null);
		this.culturalOfferService.save(offer, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyLocation() {
		CulturalOffer offer = this.testingOffer();
		offer.setLocation("");
		this.culturalOfferService.save(offer, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankLocation() {
		CulturalOffer offer = this.testingOffer();
		offer.setLocation("  ");
		this.culturalOfferService.save(offer, null);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		long size = this.culturalOfferRepository.count();
		CulturalOffer offer = this.testingOffer();
		offer.setId(CulturalOfferConstants.ID_ONE);
		offer = this.culturalOfferService.save(offer, null);
		assertEquals(size, this.culturalOfferRepository.count());
		assertEquals(CulturalOfferConstants.ID_ONE, offer.getId());
		assertEquals(CulturalOfferConstants.NON_EXISTING_NAME, offer.getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offer.getLocation());
	}
	
	public CulturalOffer testingOffer() {
		CulturalOffer offer = new CulturalOffer();
		offer.setType(this.typeRepository.findById(1l).orElse(null));
		offer.setName(CulturalOfferConstants.NON_EXISTING_NAME);
		offer.setLocation(CulturalOfferConstants.LOCATION_ONE);
		return offer;
	}
		
}
