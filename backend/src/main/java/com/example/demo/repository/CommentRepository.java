package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query("select c.rate from Comment c where c.culturalOffer.id=:id")
	public List<Integer> getRates(long id);
	
	@Transactional
	@Modifying
	@Query("delete from Comment c where c.culturalOffer.id = :culturalOfferId")
	public void deleteByCulturalOfferId(long culturalOfferId);

}
