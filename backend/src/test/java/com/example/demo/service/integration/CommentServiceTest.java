package com.example.demo.service.integration;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.example.demo.constants.UserConstants;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.CulturalOfferRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;

	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void testTotalRateMore() {
		double totalRate = this.commentService.totalRate(CommentConstants.ID_ONE);
		assertEquals(MainConstants.TOTAL_SIZE, totalRate);		//oce ovaj double uvek raditi??
	}
	
	@Test
	public void testTotalRateOne() {
		double totalRate = this.commentService.totalRate(CommentConstants.ID_FOUR);
		assertEquals(MainConstants.ONE_SIZE, totalRate);		//oce ovaj double uvek raditi??
	}
		
	@Test
	public void testTotalRateNone() {
		double totalRate = this.commentService.totalRate(MainConstants.NON_EXISTING_ID);
		assertEquals(MainConstants.NONE_SIZE, totalRate);		//oce ovaj double uvek raditi??
	}

	@Test
	public void testListMore() {
		List<Comment> comments = this.commentService.list(CulturalOfferConstants.ID_ONE, this.pageableAll).getContent();
		assertEquals(MainConstants.TOTAL_SIZE, comments.size());
		assertEquals(CommentConstants.ID_TWO, comments.get(0).getId());
		assertEquals(CommentConstants.DATE_TWO, this.format.format(comments.get(0).getCreatedAt()));
		assertEquals(CommentConstants.TEXT_TWO, comments.get(0).getText());
		assertEquals(CommentConstants.ID_ONE, comments.get(1).getId());
		assertEquals(CommentConstants.DATE_ONE, this.format.format(comments.get(1).getCreatedAt()));
		assertEquals(CommentConstants.TEXT_ONE, comments.get(1).getText());
		assertEquals(CommentConstants.ID_THREE, comments.get(2).getId());
		assertEquals(CommentConstants.DATE_THREE, this.format.format(comments.get(2).getCreatedAt()));
		assertEquals(CommentConstants.TEXT_THREE, comments.get(2).getText());
	}
	
	@Test
	public void testListMorePaginated() {
		List<Comment> comments = this.commentService.list(CulturalOfferConstants.ID_ONE, this.pageablePart).getContent();
		assertEquals(MainConstants.PART_SIZE, comments.size());
		assertEquals(CommentConstants.ID_TWO, comments.get(0).getId());
		assertEquals(CommentConstants.DATE_TWO, this.format.format(comments.get(0).getCreatedAt()));
		assertEquals(CommentConstants.TEXT_TWO, comments.get(0).getText());
		assertEquals(CommentConstants.ID_ONE, comments.get(1).getId());
		assertEquals(CommentConstants.DATE_ONE, this.format.format(comments.get(1).getCreatedAt()));
		assertEquals(CommentConstants.TEXT_ONE, comments.get(1).getText());
	}
	
	@Test
	public void testListOne() {
		List<Comment> comments = this.commentService.list(CulturalOfferConstants.ID_TWO, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, comments.size());
		assertEquals(CommentConstants.ID_FOUR, comments.get(0).getId());
		assertEquals(CommentConstants.DATE_FOUR, this.format.format(comments.get(0).getCreatedAt()));
		assertEquals(CommentConstants.TEXT_FOUR, comments.get(0).getText());
	}
	
	@Test
	public void testListNone() {
		List<Comment> comments = this.commentService.list(MainConstants.NON_EXISTING_ID, this.pageableAll).getContent();
		assertTrue(comments.isEmpty());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteExisting() {
		long size = this.commentRepository.count();
		this.commentService.delete(CommentConstants.ID_ONE);
		assertEquals(size - 1, this.commentRepository.count());
		assertNull(this.commentRepository.findById(CommentConstants.ID_ONE).orElse(null));
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	@Transactional
	@Rollback(true)
	public void testDeleteNonExisting() {
		this.commentService.delete(MainConstants.NON_EXISTING_ID);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveValid() {
		long size = this.commentRepository.count();
		Comment c = this.testingComment();
		c = this.commentService.save(c, null);
		assertEquals(size + 1, this.commentRepository.count());
		assertEquals(this.format.format(new Date()), this.format.format(c.getCreatedAt()));
		assertEquals(UserConstants.ID_ONE, c.getUser().getId());
		assertEquals(CulturalOfferConstants.ID_ONE, c.getUser().getId());
		assertEquals(CommentConstants.TEXT_ONE, c.getText());
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullDate() {
		Comment c = this.testingComment();
		c.setCreatedAt(null);
		this.commentService.save(c, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullUser() {
		Comment c = this.testingComment();
		c.setUser(null);
		this.commentService.save(c, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullOffer() {
		Comment c = this.testingComment();
		c.setCulturalOffer(null);
		this.commentService.save(c, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveNullText() {
		Comment c = this.testingComment();
		c.setText(null);
		this.commentService.save(c, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveEmptyText() {
		Comment c = this.testingComment();
		c.setText("");
		this.commentService.save(c, null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	@Transactional
	@Rollback(true)
	public void testSaveBlankText() {
		Comment c = this.testingComment();
		c.setText("  ");
		this.commentService.save(c, null);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		long size = this.commentRepository.count();
		Comment c = this.testingComment();
		c.setId(CommentConstants.ID_ONE);
		c = this.commentService.save(c, null);
		assertEquals(size, this.commentRepository.count());
		assertEquals(CommentConstants.ID_ONE, c.getId());
		assertEquals(this.format.format(new Date()), this.format.format(c.getCreatedAt()));
		assertEquals(UserConstants.ID_ONE, c.getUser().getId());
		assertEquals(CulturalOfferConstants.ID_ONE, c.getUser().getId());
		assertEquals(CommentConstants.TEXT_ONE, c.getText());
	}
	
	public Comment testingComment() {
		Comment c = new Comment();
		c.setCreatedAt(new Date());
		c.setUser(this.userRepository.findById(UserConstants.ID_ONE).orElse(null));
		c.setCulturalOffer(this.culturalOfferRepository.findById(CulturalOfferConstants.ID_ONE).orElse(null));
		c.setText(CommentConstants.TEXT_ONE);
		return c;
	}

}
