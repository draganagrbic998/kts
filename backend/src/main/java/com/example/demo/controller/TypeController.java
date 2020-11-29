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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.Constants;
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
	public ResponseEntity<List<TypeDTO>> list(@RequestParam int page, @RequestParam int size, HttpServletResponse response){
		Pageable pageable = PageRequest.of(page, size);
		Page<Type> types = this.typeService.getPage(pageable);
		response.setHeader(Constants.ENABLE_HEADER, Constants.FIRST_PAGE_HEADER + ", " + Constants.LAST_PAGE_HEADER);
		response.setHeader(Constants.FIRST_PAGE_HEADER, types.isFirst() + "");
		response.setHeader(Constants.LAST_PAGE_HEADER, types.isLast() + "");
		return new ResponseEntity<>(this.typeMapper.map(types.toList()), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
		this.typeService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
