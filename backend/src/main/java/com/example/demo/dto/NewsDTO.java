package com.example.demo.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
		this.text = news.getText();
		this.createdAt = news.getCreatedAt();
		this.images = news.getImages().stream().map(image -> image.getPath()).collect(Collectors.toList());
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
