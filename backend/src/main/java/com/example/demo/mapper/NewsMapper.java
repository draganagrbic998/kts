package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.NewsDTO;
import com.example.demo.dto.NewsUploadDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.model.News;
import com.example.demo.repository.CulturalOfferRepository;
import com.example.demo.repository.ImageRepository;

@Component
public class NewsMapper {

	@Autowired
	private CulturalOfferRepository culturalOfferRepository;

	@Autowired
	private ImageRepository imageRepository;

	public List<NewsDTO> map(List<News> news) {
		return news.stream().map(newsItem -> new NewsDTO(newsItem)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public News map(long culturalOfferId, NewsUploadDTO newsDTO) {
		CulturalOffer culturalOffer = this.culturalOfferRepository.findById(culturalOfferId).get();
		News news = new News();
		news.setId(newsDTO.getId());
		news.setText(newsDTO.getText());
		news.setCulturalOffer(culturalOffer);

		if (newsDTO.getImagePaths() != null) {
			for (String image : newsDTO.getImagePaths()) {
				news.addImage(this.imageRepository.findByPath(image));
			}
		}
		return news;
	}

}
