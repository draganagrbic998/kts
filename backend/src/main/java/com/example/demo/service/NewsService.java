package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.News;
import com.example.demo.repository.NewsRepository;

@Component
@Transactional(readOnly = true)
public class NewsService {
	
	@Autowired
	private NewsRepository newsRepository;

	@Transactional(readOnly = true)
	public Page<News> list(long culturalOfferId, Pageable pageable) {
		return this.newsRepository.findByCulturalOfferIdOrderByCreatedAtDesc(culturalOfferId, pageable);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		this.newsRepository.deleteById(id);
	}
}
