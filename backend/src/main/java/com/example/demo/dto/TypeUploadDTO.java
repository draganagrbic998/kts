package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class TypeUploadDTO {
	private String name;
	private String category;
	private MultipartFile image;
	
	public TypeUploadDTO() {
		super();
	}
	
	public TypeUploadDTO(String name, String category, MultipartFile image, String imagePath) {
		super();
		this.name = name;
		this.category = category;
		this.image = image;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	

}
