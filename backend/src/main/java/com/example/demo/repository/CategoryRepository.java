package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public Category findByName(String name);
	
	@Query("select distinct c.name from Category c where lower(c.name) like lower(concat('%',:filter,'%'))")
	public List<String> filterNames(String filter);

}
