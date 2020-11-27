package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Component
@Transactional(readOnly = true)
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Collection<Category> list() {
		return categoryRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void save(Category category) {
		this.categoryRepository.save(category);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		this.categoryRepository.deleteById(id);
	}

}
