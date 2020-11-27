package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    public Page<News> findByCulturalOfferIdOrderByCreatedAtDesc(long culturalOfferId, Pageable pageable);

}
