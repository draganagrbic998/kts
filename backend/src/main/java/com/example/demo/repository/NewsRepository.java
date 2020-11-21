package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
	@Transactional
	@Modifying
	@Query("delete from News n where n.culturalOffer.id = :culturalOfferId")
	void deleteByCulturalOfferId(long culturalOfferId);
}
