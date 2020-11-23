package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	@Autowired
	private CategoryService categoriesService;
	
	@Autowired
	private CategoryRepository categoriesRepository;
	
	@Autowired
	private CategoryMapper categoriesMapper;
	
	@GetMapping(value = "")
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<Category> sveKategorije = (List<Category>) categoriesService.findAll();
		List<CategoryDTO> sveKategorijeDTO = categoriesMapper.map(sveKategorije); 
		return new ResponseEntity<List<CategoryDTO>>(sveKategorijeDTO,HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<HttpStatus> addCategory(@RequestBody CategoryDTO catDTO ){
		Category c = categoriesMapper.map(catDTO);
		categoriesRepository.save(c);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
