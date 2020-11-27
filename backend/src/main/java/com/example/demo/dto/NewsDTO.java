package com.example.demo.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.News;

public class NewsDTO {
	
	private long id;
	private Date createdAt;
	private String text;
	private List<String> images;

	public NewsDTO() {
		super();
	}
	
	public NewsDTO(News news) {
		this.id = news.getId();
		this.createdAt = news.getCreatedAt();
		this.text = news.getText();
		this.images = news.getImages().stream().map(image -> image.getPath()).collect(Collectors.toList());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
