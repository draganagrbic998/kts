package com.example.demo.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constants.CommentConstants;
import com.example.demo.constants.CulturalOfferConstants;
import com.example.demo.constants.MainConstants;
import com.example.demo.model.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

	@Autowired
	private CommentRepository commentRepository;
	
	private Pageable pageableAll = PageRequest.of(0, 3);
	private Pageable pageablePart = PageRequest.of(0, 2);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void testTotalRateMore() {
		double totalRate = this.commentRepository.totalRate(CulturalOfferConstants.ID_ONE);
		assertEquals(MainConstants.TOTAL_SIZE, totalRate);		//oce ovaj double uvek raditi??
	}
	
	@Test
	public void testTotalRateOne() {
		double totalRate = this.commentRepository.totalRate(CulturalOfferConstants.ID_TWO);
		assertEquals(MainConstants.ONE_SIZE, totalRate);		//oce ovaj double uvek raditi??
	}
	
	@Test
	public void testTotalRateNone() {
		double totalRate = this.commentRepository.totalRate(CulturalOfferConstants.ID_THREE);
		assertEquals(MainConstants.NONE_SIZE, totalRate);		//oce ovaj double uvek raditi??
	}
	
	@Test
	public void testTotalRateNonExisting() {
		double totalRate = this.commentRepository.totalRate(MainConstants.NON_EXISTING_ID);
		assertEquals(MainConstants.NONE_SIZE, totalRate);		//oce ovaj double uvek raditi??
	}
	
	@Test
	public void testListMore() {
		List<Comment> comments = this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(CulturalOfferConstants.ID_ONE, this.pageableAll).getContent();
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
		List<Comment> comments = this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(CulturalOfferConstants.ID_ONE, this.pageablePart).getContent();
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
		List<Comment> comments = this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(CulturalOfferConstants.ID_TWO, this.pageableAll).getContent();
		assertEquals(MainConstants.ONE_SIZE, comments.size());
		assertEquals(CommentConstants.ID_FOUR, comments.get(0).getId());
		assertEquals(CommentConstants.DATE_FOUR, this.format.format(comments.get(0).getCreatedAt()));
		assertEquals(CommentConstants.TEXT_FOUR, comments.get(0).getText());
	}
	
	@Test
	public void testListNone() {
		List<Comment> comments = this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(CulturalOfferConstants.ID_THREE, this.pageableAll).getContent();
		assertTrue(comments.isEmpty());
	}
	
	@Test
	public void testListNonExisting() {
		List<Comment> comments = this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(MainConstants.NON_EXISTING_ID, this.pageableAll).getContent();
		assertTrue(comments.isEmpty());
	}
	
}
