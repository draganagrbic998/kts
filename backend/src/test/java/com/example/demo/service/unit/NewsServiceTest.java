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
import com.example.demo.repository.CulturalOfferRepository;
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
	
	@MockBean
	private CulturalOfferRepository culturalOfferRepository;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testSaveValid() {
		Mockito.when(this.newsRepository.count()).thenReturn((long) (MainConstants.TOTAL_SIZE + 1));
		long size = this.newsRepository.count();
		News n = this.testingNews();
		Mockito.when(this.newsRepository.save(n)).thenReturn(n);
		List<String> emails = new ArrayList<String>();
		emails.add("nikolicpetar91@yahoo.com");
		emails.add("nikolicpetar91@gmail.com");
		Mockito.when(this.userFollowingRepository.subscribedEmails(n.getCulturalOffer().getId())).thenReturn(emails);
		n = this.newsService.save(n, null);
		Mockito.when(this.newsRepository.count()).thenReturn(size + 1);
		assertEquals(size + 1, this.newsRepository.count());
		assertEquals(this.format.format(new Date()), this.format.format(n.getCreatedAt()));
		assertEquals(CulturalOfferConstants.ID_THREE, n.getCulturalOffer().getId());
		assertEquals(NewsConstants.TEXT_ONE, n.getText());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveNullDate() {
		News n = this.testingNews();
		n.setCreatedAt(null);
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveNullOffer() {
		News n = this.testingNews();
		n.setCulturalOffer(null);
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveNullText() {
		News n = this.testingNews();
		n.setText(null);
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveEmptyText() {
		News n = this.testingNews();
		n.setText("");
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveBlankText() {
		News n = this.testingNews();
		n.setText("  ");
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test
	public void testUpdate() {
		Mockito.when(this.newsRepository.count()).thenReturn((long) (MainConstants.TOTAL_SIZE + 1));
		long size = this.newsRepository.count();
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		Mockito.when(this.newsRepository.save(n)).thenReturn(n);
		List<String> emails = new ArrayList<String>();
		emails.add("nikolicpetar91@yahoo.com");
		emails.add("nikolicpetar91@gmail.com");
		Mockito.when(this.userFollowingRepository.subscribedEmails(n.getCulturalOffer().getId())).thenReturn(emails);
		n = this.newsService.save(n, null);
		Mockito.when(this.newsRepository.count()).thenReturn(size);
		assertEquals(size, this.newsRepository.count());
		assertEquals(NewsConstants.ID_ONE, n.getId());
		assertEquals(this.format.format(new Date()), this.format.format(n.getCreatedAt()));
		assertEquals(CulturalOfferConstants.ID_THREE, n.getCulturalOffer().getId());
		assertEquals(NewsConstants.TEXT_ONE, n.getText());
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateNullDate() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setCreatedAt(null);
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNullOffer() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setCulturalOffer(null);
		Mockito.when(this.newsRepository.save(n)).thenThrow(NullPointerException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateNullText() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setText(null);
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateEmptyText() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setText("");
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}

	@Test(expected = ConstraintViolationException.class)
	public void testUpdateBlankText() {
		News n = this.testingNews();
		n.setId(NewsConstants.ID_ONE);
		n.setText("  ");
		Mockito.when(this.newsRepository.save(n)).thenThrow(ConstraintViolationException.class);
		this.newsService.save(n, null);
	}
	
	public News testingNews() {
		CulturalOffer offer = new CulturalOffer();
		offer.setId(CulturalOfferConstants.ID_THREE);
		News n = new News();
		n.setCreatedAt(new Date());
		n.setCulturalOffer(offer);
		n.setText(NewsConstants.TEXT_ONE);
		return n;
	}

}
