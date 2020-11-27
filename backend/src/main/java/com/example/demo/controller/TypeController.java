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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TypeDTO;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.model.Type;
import com.example.demo.service.TypeService;

@RestController
@RequestMapping(value = "/api/types", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAuthority('admin')")
public class TypeController {

	@Autowired
	private TypeService typeService;
		
	@Autowired
	private TypeMapper typeMapper;
	
	@GetMapping(value = "")
	public ResponseEntity<List<TypeDTO>> getTypes(){
		//msm da ovde treba paginacija
		return new ResponseEntity<List<TypeDTO>>(this.typeMapper.map((List<Type>) this.typeService.list()), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
		this.typeService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
