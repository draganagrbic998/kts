package com.example.demo.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.model.Comment;

public class CommentDTO {

	private long id;
	private Date createdAt;
	private int rate;
	private String text;
	private List<String> images;
	private String user;
	
	public CommentDTO() {
		super();
	}

	public CommentDTO(Comment comment) {
		super();
		this.id = comment.getId();
		this.createdAt = comment.getCreatedAt();
		this.rate = comment.getRate();
		this.text = comment.getText();
		this.images = comment.getImages().stream().map(image -> image.getPath()).collect(Collectors.toList());
		this.user = comment.getUser().getEmail();
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

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
