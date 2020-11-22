package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.NewsDTO;
import com.example.demo.model.News;

@Component
public class NewsMapper {
	
	public List<NewsDTO> map(List<News> newsList){
		return newsList.stream().map(news -> {
			NewsDTO newsDTO = new NewsDTO(news);
			return newsDTO;
		}).collect(Collectors.toList());
	}

}
