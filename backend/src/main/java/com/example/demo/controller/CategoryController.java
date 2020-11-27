package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAuthority('admin')")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
		
	@Autowired
	private CategoryMapper categoryMapper;
	
	@GetMapping(value = "")
	public ResponseEntity<List<CategoryDTO>> list(){
		//msm da treba i ovde paginacija
		return new ResponseEntity<>(this.categoryMapper.map((List<Category>) this.categoryService.list()), HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<HttpStatus> save(@RequestBody CategoryDTO categoryDTO){
		this.categoryService.save(this.categoryMapper.map(categoryDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
		this.categoryService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
