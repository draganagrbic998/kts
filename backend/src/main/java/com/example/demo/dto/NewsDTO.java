package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Image;
import com.example.demo.model.News;

public class NewsDTO {
	
	private long id;
	private String text;
	private Date createdAt;
	private List<String> images;

	public NewsDTO() {
		super();
	}
	
	public NewsDTO(News news) {
		this.id = news.getId();
		this.setText(news.getText());
		this.setCreatedAt(news.getCreatedAt());
		this.images = new ArrayList<>();

		for (Image img: news.getImages())
			this.images.add(img.getPath());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
