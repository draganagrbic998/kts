package com.example.demo.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.constants.NewsConstants;
import com.example.demo.model.CulturalOffer;
import com.example.demo.model.News;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.UserFollowingRepository;
import com.example.demo.service.NewsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {

	@Autowired
	private NewsService newsService;

	@MockBean
	private NewsRepository newsRepository;

	@MockBean
	private UserFollowingRepository userFollowingRepository;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testAddValid() {
		Mockito.when(this.newsRepository.count())
		.thenReturn((long) (MainConstants.TOTAL_SIZE + 1));
		long size = this.newsRepository.count();
		News news = this.testingNews();
		Mockito.when(this.newsRepository.save(news)).thenReturn(news);
		List<String> emails = new ArrayList<String>();
		emails.add("nikolicpetar91@yahoo.com");
		emails.add("nikolicpetar91@gmail.com");
		Mockito.when(this.userFollowingRepository.subscribedEmails(news.getCulturalOffer().getId()))
		.thenReturn(emails);
		news = this.newsService.save(news, null);
		Mockito.when(this.newsRepository.count())
		.thenReturn(size + 1);
		assertEquals(size + 1, this.newsRepository.count());
		assertEquals(CulturalOfferConstants.ID_THREE, news.getCulturalOffer().getId());
		assertEquals(this.format.format(new Date()), this.format.format(news.getCreatedAt()));
		assertEquals(NewsConstants.TEXT_ONE, news.getText());
	}

	@Test(expected = NullPointerException.class)
	public void testAddNullOffer() {
		News news = this.testingNews();
		news.setCulturalOffer(null);
		this.newsService.save(news, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testAddNullDate() {
		News news = this.testingNews();
		news.setCreatedAt(null);
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddNullText() {
		News news = this.testingNews();
		news.setText(null);
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddEmptyText() {
		News news = this.testingNews();
		news.setText("");
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddBlankText() {
		News news = this.testingNews();
		news.setText("  ");
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.newsRepository.count())
		.thenReturn((long) (MainConstants.TOTAL_SIZE + 1));
		long size = this.newsRepository.count();
		News news = this.testingNews();
		news.setId(NewsConstants.ID_ONE);
		Mockito.when(this.newsRepository.save(news))
		.thenReturn(news);
		List<String> emails = new ArrayList<String>();
		emails.add("nikolicpetar91@yahoo.com");
		emails.add("nikolicpetar91@gmail.com");
		Mockito.when(this.userFollowingRepository.subscribedEmails(news.getCulturalOffer().getId()))
		.thenReturn(emails);
		news = this.newsService.save(news, null);
		assertEquals(size, this.newsRepository.count());
		assertEquals(NewsConstants.ID_ONE, news.getId());
		assertEquals(CulturalOfferConstants.ID_THREE, news.getCulturalOffer().getId());
		assertEquals(this.format.format(new Date()), this.format.format(news.getCreatedAt()));
		assertEquals(NewsConstants.TEXT_ONE, news.getText());
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNullOffer() {
		News news = this.testingNews();
		news.setId(NewsConstants.ID_ONE);
		news.setCulturalOffer(null);
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(NullPointerException.class);
		this.newsService.save(news, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateNullDate() {
		News news = this.testingNews();
		news.setId(NewsConstants.ID_ONE);
		news.setCreatedAt(null);
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateNullText() {
		News news = this.testingNews();
		news.setId(NewsConstants.ID_ONE);
		news.setText(null);
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateEmptyText() {
		News news = this.testingNews();
		news.setId(NewsConstants.ID_ONE);
		news.setText("");
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateBlankText() {
		News news = this.testingNews();
		news.setId(NewsConstants.ID_ONE);
		news.setText("  ");
		Mockito.when(this.newsRepository.save(news))
		.thenThrow(ConstraintViolationException.class);
		this.newsService.save(news, null);
	}
	
	public News testingNews() {
		News news = new News();
		CulturalOffer offer = new CulturalOffer();
		offer.setId(CulturalOfferConstants.ID_THREE);
		news.setCulturalOffer(offer);
		news.setText(NewsConstants.TEXT_ONE);
		return news;
	}

}
