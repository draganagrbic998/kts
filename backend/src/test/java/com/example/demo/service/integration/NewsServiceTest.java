package com.example.demo.service.integration;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.CommentConstants;
import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.NewsConstants;
import com.example.demo.dto.FilterParamsNewsDTO;
import com.example.demo.model.News;
import com.example.demo.repository.CulturalOfferRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.NewsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private CulturalOfferRepository culturalOfferRepository;

	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);
	private Pageable pageableNonExisting = PageRequest.of(MainConstants.ONE_SIZE, MainConstants.TOTAL_SIZE);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testListMore() {
		List<News> news = this.newsService
				.filter(CulturalOfferConstants.ID_THREE, new FilterParamsNewsDTO(), this.pageableAll).getContent();
		assertEquals(MainConstants.TOTAL_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(0).getCulturalOffer().getId());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(1).getCulturalOffer().getId());
		assertEquals(NewsConstants.ID_THREE, news.get(2).getId());
		assertEquals(NewsConstants.DATE_THREE, this.format.format(news.get(2).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_THREE, news.get(2).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(2).getCulturalOffer().getId());
	}

	@Test
	public void testListMorePaginated() {
		List<News> news = this.newsService
				.filter(CulturalOfferConstants.ID_THREE, new FilterParamsNewsDTO(), this.pageablePart).getContent();
		assertEquals(MainConstants.PART_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(0).getCulturalOffer().getId());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(1).getCulturalOffer().getId());
	}

	@Test
	public void testListMoreNonExistingPage() {
		List<News> news = 
				this.newsService
				.filter(CulturalOfferConstants.ID_THREE, new FilterParamsNewsDTO(), this.pageableNonExisting).getContent();
		assertTrue(news.isEmpty());
	}

	@Test
	public void testListOne() {
		List<News> news = this.newsService
				.filter(CulturalOfferConstants.ID_TWO, new FilterParamsNewsDTO(), this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, news.size());
		assertEquals(NewsConstants.ID_ONE, news.get(0).getId());
		assertEquals(NewsConstants.DATE_ONE, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_ONE, news.get(0).getText());
		assertEquals(CulturalOfferConstants.ID_TWO, news.get(0).getCulturalOffer().getId());
	}

	@Test
	public void testListNone() {
		List<News> news = this.newsService
				.filter(MainConstants.NON_EXISTING_ID, new FilterParamsNewsDTO(), this.pageableAll).getContent();
		assertTrue(news.isEmpty());
	}

	@Test
	public void testListNoneDates() throws ParseException {
		FilterParamsNewsDTO filters = new FilterParamsNewsDTO(this.format.parse(NewsConstants.WRONG_START),
				this.format.parse(NewsConstants.WRONG_END));
		List<News> news = this.newsService.filter(MainConstants.NON_EXISTING_ID, filters, this.pageableAll)
				.getContent();
		assertTrue(news.isEmpty());
	}

	@Test
	public void testListOneDates() throws ParseException {
		FilterParamsNewsDTO filters = new FilterParamsNewsDTO(this.format.parse(NewsConstants.START_DATE_ONE),
				this.format.parse(NewsConstants.END_DATE_ONE));
		List<News> news = this.newsService.filter(CulturalOfferConstants.ID_THREE, filters, this.pageableAll)
				.getContent();
		assertEquals(MainConstants.ONE_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(0).getCulturalOffer().getId());
	}

	@Test
	public void testListMoreDatesPaginated() throws ParseException {
		FilterParamsNewsDTO filters = new FilterParamsNewsDTO(this.format.parse(NewsConstants.START_DATE_MORE),
				this.format.parse(NewsConstants.END_DATE_MORE));
		List<News> news = this.newsService.filter(CulturalOfferConstants.ID_THREE, filters, this.pageablePart)
				.getContent();
		assertEquals(MainConstants.PART_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(0).getCulturalOffer().getId());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(1).getCulturalOffer().getId());
	}

	@Test
	public void testListMoreDates() throws ParseException {
		FilterParamsNewsDTO filters = new FilterParamsNewsDTO(this.format.parse(NewsConstants.START_DATE_MORE),
				this.format.parse(NewsConstants.END_DATE_MORE));
		List<News> news = this.newsService.filter(CulturalOfferConstants.ID_THREE, filters, this.pageableAll)
				.getContent();
		assertEquals(MainConstants.TOTAL_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(0).getCulturalOffer().getId());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(1).getCulturalOffer().getId());
		assertEquals(NewsConstants.ID_THREE, news.get(2).getId());
		assertEquals(NewsConstants.DATE_THREE, this.format.format(news.get(2).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_THREE, news.get(2).getText());
		assertEquals(CulturalOfferConstants.ID_THREE, news.get(2).getCulturalOffer().getId());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteExisting() {
		long size = this.newsRepository.count();
		this.newsService.delete(NewsConstants.ID_ONE);
		assertEquals(size - 1, this.newsRepository.count());
		assertNull(this.newsRepository.findById(CommentConstants.ID_ONE).orElse(null));
	}

	@Test(expected = EmptyResultDataAccessException.class)
	@Transactional
	@Rollback(true)
	public void testDeleteNonExisting() {
		this.newsService.delete(MainConstants.NON_EXISTING_ID);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSaveValid() {
		long size = this.newsRepository.count();
		News n = this.testingNews();
		n = this.newsService.save(n, null);
		assertEquals(size + 1, this.newsRepository.count());
		assertEquals(this.format.format(new Date()), this.format.format(n.getCreatedAt()));
		assertEquals(CulturalOfferConstants.ID_THREE, n.getCulturalOffer().getId());
		assertEquals(NewsConstants.TEXT_ONE, n.getText());
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullDate() {
		News n = this.testingNews();
		n.setCreatedAt(null);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullOffer() {
		News n = this.testingNews();
		n.setCulturalOffer(null);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullText() {
		News n = this.testingNews();
		n.setText(null);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyText() {
		News n = this.testingNews();
		n.setText("");
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankText() {
		News n = this.testingNews();
		n.setText("  ");
		this.newsService.save(n, null);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		long size = this.newsRepository.count();
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n = this.newsService.save(n, null);
		assertEquals(size, this.newsRepository.count());
		assertEquals(NewsConstants.ID_ONE, n.getId());
		assertEquals(this.format.format(new Date()), this.format.format(n.getCreatedAt()));
		assertEquals(CulturalOfferConstants.ID_THREE, n.getCulturalOffer().getId());
		assertEquals(NewsConstants.TEXT_ONE, n.getText());
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateNullDate() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setCreatedAt(null);
		this.newsService.save(n, null);
		this.newsRepository.count();
	}

	@Test(expected = NullPointerException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateNullOffer() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setCulturalOffer(null);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateNullText() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setText(null);
		this.newsService.save(n, null);
		this.newsRepository.count();
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateEmptyText() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setText("");
		this.newsService.save(n, null);
		this.newsRepository.count();
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testUpdateBlankText() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setText("  ");
		this.newsService.save(n, null);
		this.newsRepository.count();
	}
	
	public News testingNews() {
		News n = new News();
		n.setCreatedAt(new Date());
		n.setCulturalOffer(this.culturalOfferRepository.findById(CulturalOfferConstants.ID_THREE).orElse(null));
		n.setText(NewsConstants.TEXT_ONE);
		return n;
	}

}
