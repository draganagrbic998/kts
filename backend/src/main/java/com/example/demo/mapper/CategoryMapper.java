package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;

@Component
public class CategoryMapper {
	
	public Category map(CategoryDTO categoryDTO) {
		Category c = new Category();
		c.setId(categoryDTO.getId());
		c.setName(categoryDTO.getName());
		return c;
	}
	
	public List<CategoryDTO> map(List<Category> categories) {
		return categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
	}
	
}
