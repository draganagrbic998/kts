package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;

@Component
public class CategoryMapper {
	public List<CategoryDTO> map(List<Category> cats) {
		List<CategoryDTO> ret = new ArrayList<CategoryDTO>();
		for(Category c: cats) {
			CategoryDTO cdto = new CategoryDTO(c.getId(),c.getName());
			ret.add(cdto);
		}
		return ret;
	}
	
	
}
