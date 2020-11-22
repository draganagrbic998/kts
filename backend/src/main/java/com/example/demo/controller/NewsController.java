package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.Constants;
import com.example.demo.dto.NewsDTO;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.model.News;
import com.example.demo.service.NewsService;

@RestController
@RequestMapping(value = "/api/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsMapper newsMapper;
	
	@PostMapping(value = "/list")
	public ResponseEntity<List<NewsDTO>> getNews(@RequestBody long culturalOfferId, @RequestParam int page, @RequestParam int size, HttpServletResponse response){
		Pageable pageable = PageRequest.of(page, size);
		Page<News> newsList = this.newsService.getPage(culturalOfferId, pageable);
		response.setHeader(Constants.ENABLE_HEADER, Constants.FIRST_PAGE_HEADER + ", " + Constants.LAST_PAGE_HEADER);
		response.setHeader(Constants.FIRST_PAGE_HEADER, newsList.isFirst() + "");
		response.setHeader(Constants.LAST_PAGE_HEADER, newsList.isLast() + "");
		return new ResponseEntity<>(this.newsMapper.map(newsList.toList()), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{newsId}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long newsId) {
		this.newsService.delete(newsId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
