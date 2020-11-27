package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.NewsDTO;
import com.example.demo.model.News;

@Component
public class NewsMapper {
	
	public List<NewsDTO> map(List<News> news){
		return news.stream().map(newsItem -> new NewsDTO(newsItem)).collect(Collectors.toList());
	}

}
