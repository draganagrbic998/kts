package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.FilterParamsNewsDTO;
import com.example.demo.model.Image;
import com.example.demo.model.News;
import com.example.demo.repository.NewsRepository;

@Component
@Transactional(readOnly = true)
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private ImageService imageService;

	@Transactional(readOnly = true)
	public Page<News> filter(long culturalOfferId, FilterParamsNewsDTO filters, Pageable pageable) {
		return this.newsRepository.filter(culturalOfferId, filters.getStartDate(), filters.getEndDate(), pageable);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		this.newsRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	public News save(News news, List<MultipartFile> uploads) throws FileNotFoundException, IOException {
		if (uploads != null) {
			for (MultipartFile mpf : uploads) {
				Image image = new Image(this.imageService.store(mpf));
				news.addImage(image);
				this.imageService.save(image);
			}
		}
		return this.newsRepository.save(news);
	}
}
