package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

public class TypeUploadDTO {
	
	private String name;
	private String category;
	private MultipartFile placemarkIcon;
	
	public TypeUploadDTO() {
		super();
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

	public MultipartFile getPlacemarkIcon() {
		return placemarkIcon;
	}

	public void setPlacemarkIcon(MultipartFile placemarkIcon) {
		this.placemarkIcon = placemarkIcon;
	}

}
