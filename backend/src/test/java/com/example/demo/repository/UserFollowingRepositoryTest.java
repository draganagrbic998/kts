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
import com.example.demo.constants.UserConstants;
import com.example.demo.model.CulturalOffer;
import com.example.demo.model.UserFollowing;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFollowingRepositoryTest {
	
	@Autowired
	private UserFollowingRepository userFollowingRepository;
	
	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);
	
	@Test
	public void testFindExisting() {
		UserFollowing uf = this.userFollowingRepository.findByUserIdAndCulturalOfferId(UserConstants.ID_ONE, CulturalOfferConstants.ID_ONE);
		assertNotNull(uf);
	}
	
	@Test
	public void testFindNonExisting() {
		UserFollowing uf = this.userFollowingRepository.findByUserIdAndCulturalOfferId(UserConstants.ID_TWO, CulturalOfferConstants.ID_TWO);
		assertNull(uf);
	}
	
	@Test
	public void testFindNonExistingNoUser() {
		UserFollowing uf = this.userFollowingRepository.findByUserIdAndCulturalOfferId(MainConstants.NON_EXISTING_ID, CulturalOfferConstants.ID_ONE);
		assertNull(uf);
	}
	
	@Test
	public void testFindNonExistingNoOffer() {
		UserFollowing uf = this.userFollowingRepository.findByUserIdAndCulturalOfferId(UserConstants.ID_ONE, MainConstants.NON_EXISTING_ID);
		assertNull(uf);
	}
	
	@Test
	public void testFilterEmpty() {
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, this.pageableAll).getContent();
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
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, this.pageablePart).getContent();
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
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, CulturalOfferConstants.FILTER_NAME_ALL, CulturalOfferConstants.FILTER_LOCATION_ALL, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageableAll).getContent();
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
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, CulturalOfferConstants.FILTER_NAME_ALL, CulturalOfferConstants.FILTER_LOCATION_ALL, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageablePart).getContent();
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
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, FilterConstants.FILTER_ONE, CulturalOfferConstants.FILTER_LOCATION_ALL, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneLocation() {
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, CulturalOfferConstants.FILTER_NAME_ALL, FilterConstants.FILTER_ONE, CulturalOfferConstants.FILTER_TYPE_ALL, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneType() {
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, CulturalOfferConstants.FILTER_NAME_ALL, CulturalOfferConstants.FILTER_LOCATION_ALL, FilterConstants.FILTER_ONE, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterOneNameLocationType() {
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, FilterConstants.FILTER_ONE, FilterConstants.FILTER_ONE, FilterConstants.FILTER_ONE, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, offers.size());
		assertEquals(CulturalOfferConstants.ID_ONE, offers.get(0).getId());
		assertEquals(CulturalOfferConstants.NAME_ONE, offers.get(0).getName());
		assertEquals(CulturalOfferConstants.LOCATION_ONE, offers.get(0).getLocation());
	}
	
	@Test
	public void testFilterNone() {
		List<CulturalOffer> offers = this.userFollowingRepository.filter(UserConstants.ID_ONE, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, FilterConstants.FILTER_NONE, this.pageableAll).getContent();
		assertTrue(offers.isEmpty());
	}
	
	@Test
	public void testFilterNoneNoUserFollowing() {
		List<CulturalOffer> offers = this.userFollowingRepository.filter(MainConstants.NON_EXISTING_ID, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, FilterConstants.FILTER_ALL, this.pageableAll).getContent();
		assertTrue(offers.isEmpty());
	}
	
	@Test
	public void testEmailsMore() {
		List<String> emails = this.userFollowingRepository.subscribedEmails(CulturalOfferConstants.ID_ONE);
		assertEquals(MainConstants.PART_SIZE, emails.size());
		assertTrue(emails.contains(UserConstants.EMAIL_ONE));
		assertTrue(emails.contains(UserConstants.EMAIL_TWO));
	}
	
	@Test
	public void testEmailsOne() {
		List<String> emails = this.userFollowingRepository.subscribedEmails(CulturalOfferConstants.ID_TWO);
		assertEquals(MainConstants.ONE_SIZE, emails.size());
		assertEquals(UserConstants.EMAIL_ONE, emails.get(0));
	}
	
	@Test
	public void testEmailsNone() {
		List<String> emails = this.userFollowingRepository.subscribedEmails(MainConstants.NON_EXISTING_ID);
		assertTrue(emails.isEmpty());
	}

}
