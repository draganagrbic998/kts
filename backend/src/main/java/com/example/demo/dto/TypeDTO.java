package com.example.demo.dto;

public class TypeDTO {
	private Long id;
	private String name;
	private String category;
	private String placemark;
	

	public TypeDTO() {
		super();
	}
	
	public TypeDTO(Long id, String name, String category, String placemark) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.placemark = placemark;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPlacemark() {
		return placemark;
	}
	public void setPlacemark(String placemark) {
		this.placemark = placemark;
	}
	
}
