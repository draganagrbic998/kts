package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;

@Component
public class CategoryMapper {
	
	@Transactional(readOnly = true)
	public Category map(CategoryDTO categoryDTO) {
		Category c = new Category();
		c.setId(categoryDTO.getId());
		c.setName(categoryDTO.getName());
		return c;
	}
	
	@Transactional
	public CategoryDTO map(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO(category);
		return categoryDTO;
	}
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> map(List<Category> categories) {
		return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
	}
	
}
