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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.FilterConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.TypeConstants;
import com.example.demo.model.CulturalOffer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CulturalOfferRepositoryTest {
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);

	@Test
	public void testHasNameNewOfferNonExisting() {
		CulturalOffer co = this.culturalOfferRepository.hasName(null, CulturalOfferConstants.NON_EXISTING_NAME);
		assertNull(co);
	}
	
	@Test
	public void testHasNameNewOfferExisting() {
		CulturalOffer co = this.culturalOfferRepository.hasName(null, CulturalOfferConstants.NAME_ONE);
		assertNotNull(co);
	}
	
	@Test
	public void testHasNameOldOfferOwnName() {
		CulturalOffer co = this.culturalOfferRepository.hasName(CulturalOfferConstants.ID_ONE, CulturalOfferConstants.NAME_ONE);
		assertNull(co);
	}
	
	@Test
	public void testHasNameOldOfferNonExisting() {
		CulturalOffer co = this.culturalOfferRepository.hasName(CulturalOfferConstants.ID_ONE, CulturalOfferConstants.NON_EXISTING_NAME);
		assertNull(co);
	}
	
	@Test
	public void testHasNameOldOfferExisting() {
		CulturalOffer co = this.culturalOfferRepository.hasName(CulturalOfferConstants.ID_ONE, CulturalOfferConstants.NAME_TWO);
		assertNotNull(co);
	}
	
	@Test
	public void testFilterNamesEmpty() {
		List<String> names = this.culturalOfferRepository.filterNames(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CulturalOfferConstants.NAME_ONE));
		assertTrue(names.contains(CulturalOfferConstants.NAME_TWO));
		assertTrue(names.contains(CulturalOfferConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesAll() {
		List<String> names = this.culturalOfferRepository.filterNames(CulturalOfferConstants.FILTER_NAME_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, names.size());
		assertTrue(names.contains(CulturalOfferConstants.NAME_ONE));
		assertTrue(names.contains(CulturalOfferConstants.NAME_TWO));
		assertTrue(names.contains(CulturalOfferConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterNamesOne() {
		List<String> names = this.culturalOfferRepository.filterNames(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, names.size());
		assertEquals(CulturalOfferConstants.NAME_ONE, names.get(0));
	}
	
	@Test
	public void testFilterNamesNone() {
		List<String> names = this.culturalOfferRepository.filterNames(FilterConstants.FILTER_NONE);
		assertTrue(names.isEmpty());
	}
	
	@Test
	public void testFilterLocationsEmpty() {
		List<String> locations = this.culturalOfferRepository.filterLocations(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, locations.size());
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_ONE));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_TWO));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_THREE));
	}
	
	@Test
	public void testFilterLocationsAll() {
		List<String> locations = this.culturalOfferRepository.filterLocations(CulturalOfferConstants.FILTER_LOCATION_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, locations.size());
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_ONE));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_TWO));
		assertTrue(locations.contains(CulturalOfferConstants.LOCATION_THREE));
	}
	
	@Test
	public void testFilterLocationsOne() {
		List<String> locations = this.culturalOfferRepository.filterLocations(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, locations.size());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, locations.get(0));
	}
	
	@Test
	public void testFilterLocationsNone() {
		List<String> locations = this.culturalOfferRepository.filterLocations(FilterConstants.FILTER_NONE);
		assertTrue(locations.isEmpty());
	}
	
	@Test
	public void testFilterTypesEmpty() {
		List<String> types = this.culturalOfferRepository.filterTypes(FilterConstants.FILTER_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, types.size());
		assertTrue(types.contains(TypeConstants.NAME_ONE));
		assertTrue(types.contains(TypeConstants.NAME_TWO));
		assertTrue(types.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterTypesAll() {
		List<String> types = this.culturalOfferRepository.filterTypes(CulturalOfferConstants.FILTER_TYPE_ALL);
		assertEquals(MainConstants.TOTAL_SIZE, types.size());
		assertTrue(types.contains(TypeConstants.NAME_ONE));
		assertTrue(types.contains(TypeConstants.NAME_TWO));
		assertTrue(types.contains(TypeConstants.NAME_THREE));
	}
	
	@Test
	public void testFilterTypesOne() {
		List<String> types = this.culturalOfferRepository.filterTypes(FilterConstants.FILTER_ONE);
		assertEquals(MainConstants.ONE_SIZE, types.size());
		assertEquals(TypeConstants.NAME_ONE, types.get(0));
	}
	
	@Test
	public void testFilterTypesNone() {
		List<String> types = this.culturalOfferRepository.filterTypes(FilterConstants.FILTER_NONE);
		assertTrue(types.isEmpty());
	}
		
	@Test
	public void testFilterEmpty() {
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, this.pageableAll).getContent();
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
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, this.pageablePart).getContent();
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
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(CulturalOfferConstants.FILTER_NAME_ALL, CulturalOfferConstants.FILTER_LOCATION_ALL, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageableAll).getContent();
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
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(CulturalOfferConstants.FILTER_NAME_ALL, CulturalOfferConstants.FILTER_LOCATION_ALL, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageablePart).getContent();
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
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(FilterConstants.FILTER_ONE, CulturalOfferConstants.FILTER_LOCATION_ALL, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneLocation() {
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(CulturalOfferConstants.FILTER_NAME_ALL, FilterConstants.FILTER_ONE, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneType() {
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(CulturalOfferConstants.FILTER_NAME_ALL, CulturalOfferConstants.FILTER_LOCATION_ALL, FilterConstants.FILTER_ONE, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneNameLocationType() {
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(FilterConstants.FILTER_ONE, FilterConstants.FILTER_ONE, FilterConstants.FILTER_ONE, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterNone() {
		List<CulturalOffer> offers = this.culturalOfferRepository.filter(FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, FilterConstants.FILTER_NONE, this.pageableAll).getContent();
		assertTrue(offers.isEmpty());
	}
	
}
