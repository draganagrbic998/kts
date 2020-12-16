package com.example.demo.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.NewsConstants;
import com.example.demo.model.News;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {

	@Autowired
	private NewsRepository newsRepository;

	private Pageable pageableAll = PageRequest.of(MainConstants.NONE_SIZE, MainConstants.TOTAL_SIZE);
	private Pageable pageablePart = PageRequest.of(MainConstants.NONE_SIZE, MainConstants.PART_SIZE);
	private SimpleDateFormat format = new SimpleDateFormat(MainConstants.DATE_FORMAT);

	@Test
	public void testListNonExisting() {
		List<News> news = this.newsRepository.filter(MainConstants.NON_EXISTING_ID, null, null, this.pageableAll)
				.getContent();
		assertTrue(news.isEmpty());
	}

	@Test
	public void testListNoneCulturalOffer() {
		List<News> news = this.newsRepository.filter(CulturalOfferConstants.ID_ONE, null, null, this.pageableAll)
				.getContent();
		assertTrue(news.isEmpty());
	}

	@Test
	public void testListNoneDates() throws ParseException {
		List<News> news = this.newsRepository
				.filter(CulturalOfferConstants.ID_TWO, this.format.parse(NewsConstants.WRONG_START),
						this.format.parse(NewsConstants.WRONG_END), this.pageableAll)
				.getContent();
		assertTrue(news.isEmpty());
	}

	@Test
	public void testListOne() {
		List<News> news = this.newsRepository.filter(CulturalOfferConstants.ID_TWO, null, null, this.pageableAll)
				.getContent();
		assertEquals(MainConstants.ONE_SIZE, news.size());
		assertEquals(NewsConstants.ID_ONE, news.get(0).getId());
		assertEquals(NewsConstants.DATE_ONE, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_ONE, news.get(0).getText());
	}

	@Test
	public void testListMorePaginated() {
		List<News> news = this.newsRepository.filter(CulturalOfferConstants.ID_THREE, null, null, this.pageablePart)
				.getContent();
		assertEquals(MainConstants.PART_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
	}

	@Test
	public void testListMore() {
		List<News> news = this.newsRepository.filter(CulturalOfferConstants.ID_THREE, null, null, this.pageableAll)
				.getContent();
		assertEquals(MainConstants.TOTAL_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
		assertEquals(NewsConstants.ID_THREE, news.get(2).getId());
		assertEquals(NewsConstants.DATE_THREE, this.format.format(news.get(2).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_THREE, news.get(2).getText());
	}

	@Test
	public void testListOneDates() throws ParseException {
		List<News> news = this.newsRepository
				.filter(CulturalOfferConstants.ID_THREE, this.format.parse(NewsConstants.START_DATE_ONE),
						this.format.parse(NewsConstants.END_DATE_ONE), this.pageableAll)
				.getContent();
		assertEquals(MainConstants.ONE_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
	}

	@Test
	public void testListMoreDatesPaginated() throws ParseException {
		List<News> news = this.newsRepository
				.filter(CulturalOfferConstants.ID_THREE, this.format.parse(NewsConstants.START_DATE_MORE),
						this.format.parse(NewsConstants.END_DATE_MORE), this.pageablePart)
				.getContent();
		assertEquals(MainConstants.PART_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
	}

	@Test
	public void testListMoreDates() throws ParseException {
		List<News> news = this.newsRepository
				.filter(CulturalOfferConstants.ID_THREE, this.format.parse(NewsConstants.START_DATE_MORE),
						this.format.parse(NewsConstants.END_DATE_MORE), this.pageableAll)
				.getContent();
		assertEquals(MainConstants.TOTAL_SIZE, news.size());
		assertEquals(NewsConstants.ID_FOUR, news.get(0).getId());
		assertEquals(NewsConstants.DATE_FOUR, this.format.format(news.get(0).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_FOUR, news.get(0).getText());
		assertEquals(NewsConstants.ID_TWO, news.get(1).getId());
		assertEquals(NewsConstants.DATE_TWO, this.format.format(news.get(1).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_TWO, news.get(1).getText());
		assertEquals(NewsConstants.ID_THREE, news.get(2).getId());
		assertEquals(NewsConstants.DATE_THREE, this.format.format(news.get(2).getCreatedAt()));
		assertEquals(NewsConstants.TEXT_THREE, news.get(2).getText());
	}
}
