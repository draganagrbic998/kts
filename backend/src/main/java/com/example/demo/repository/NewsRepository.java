package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

	@Query("select n from News n where n.culturalOffer.id = :culturalOfferId")
    public Page<News> getPage(long culturalOfferId, Pageable pageable);

}
