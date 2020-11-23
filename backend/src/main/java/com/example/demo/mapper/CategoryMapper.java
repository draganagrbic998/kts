package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;

@Component
public class CategoryMapper {
	public List<CategoryDTO> map(List<Category> cats) {
		return cats.stream().map(cat -> {
			CategoryDTO catDTO = new CategoryDTO(cat.getId(),cat.getName());
			return catDTO;
		}).collect(Collectors.toList());
	}

	public Category map(CategoryDTO catDTO) {
		Category c = new Category();
		c.setName(catDTO.getName());
		return c;
	}
	
	
}
