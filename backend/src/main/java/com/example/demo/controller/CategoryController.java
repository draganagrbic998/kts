package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.Constants;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.UniqueCheckDTO;
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
	public ResponseEntity<List<CategoryDTO>> list(@RequestParam int page, @RequestParam int size, HttpServletResponse response){
		Pageable pageable = PageRequest.of(page, size);
		Page<Category> categories = this.categoryService.list(pageable);
		response.setHeader(Constants.ENABLE_HEADER, Constants.FIRST_PAGE_HEADER + ", " + Constants.LAST_PAGE_HEADER);
		response.setHeader(Constants.FIRST_PAGE_HEADER, categories.isFirst() + "");
		response.setHeader(Constants.LAST_PAGE_HEADER, categories.isLast() + "");
		return new ResponseEntity<>(this.categoryMapper.map(categories.toList()), HttpStatus.OK);
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
	
	@PostMapping(value = "/has_name")
	public ResponseEntity<Boolean> hasName(@RequestBody UniqueCheckDTO param) {
		return new ResponseEntity<>(this.categoryService.hasName(param), HttpStatus.OK);
	}
		
	@PostMapping(value = "/filter_names")
	public ResponseEntity<List<String>> filterNames(@RequestBody String filter){
		return new ResponseEntity<>(this.categoryService.filterNames(filter), HttpStatus.OK);
	}
	
}
